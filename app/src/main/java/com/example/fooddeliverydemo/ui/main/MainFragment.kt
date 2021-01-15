package com.example.fooddeliverydemo.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.airbnb.mvrx.*
import com.example.fooddeliverydemo.R
import com.example.fooddeliverydemo.databinding.MainFragmentBinding
import com.example.fooddeliverydemo.ui.adapter.CategoryViewPagerAdapter
import com.example.fooddeliverydemo.ui.adapter.PromoViewPagerAdapter
import com.example.fooddeliverydemo.utils.viewbinding.viewBinding
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainFragment : NoConstructorFragment() {


    private val viewModel: MainViewModel by fragmentViewModel()

    @Inject
    lateinit var viewModelFactory: MainViewModel.Factory

    private val binding by viewBinding(MainFragmentBinding::bind)

    private val categoryAdapter = CategoryViewPagerAdapter {
        viewModel.addToCart(it)
    }

    private val promoAdapter = PromoViewPagerAdapter()
    lateinit var badgeDrawable: BadgeDrawable

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.contentHome.categoryViewPager.adapter = categoryAdapter
        binding.contentHome.promoViewpager.adapter = promoAdapter
        binding.contentHome.promoViewpager.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.contentHome.piv.setSelected(position)
            }
        })
        TabLayoutMediator(binding.contentHome.categoryTabs, binding.contentHome.categoryViewPager) { tab, position ->
            tab.text = categoryAdapter.getCategory(position).name
        }.attach()
        badgeDrawable = BadgeDrawable.create(requireContext())
        badgeDrawable.badgeGravity = BadgeDrawable.TOP_END
        //Important to change the position of the Badge
        badgeDrawable.horizontalOffset = 30
        badgeDrawable.verticalOffset = 20
        badgeDrawable.number = 0
        binding.contentHome.fab.viewTreeObserver.addOnGlobalLayoutListener {
            if (getView() == null) return@addOnGlobalLayoutListener
            BadgeUtils.attachBadgeDrawable(badgeDrawable, binding.contentHome.fab, binding.contentHome.frame)
//            binding.contentHome.fab.viewTreeObserver.removeOnGlobalLayoutListener(this@addOnGlobalLayoutListener)
        }
        binding.contentHome.fab.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_cartFragment)

        }
    }

    override fun invalidate() {
        withState(viewModel) { state ->
            when (val status = state.categoryWithFood) {
                Uninitialized -> {

                }
                is Loading -> {
                    categoryAdapter.submitList(status())
                }
                is Success -> categoryAdapter.submitList(status())
                is Fail -> {
                    Toast.makeText(requireContext(), status.error.message, Toast.LENGTH_LONG).show()
                }
            }
            when (val status = state.promo) {
                Uninitialized -> {
                }
                is Loading -> {
                    promoAdapter.submitList(status())
                    binding.contentHome.piv.count = status()?.size ?: 0
                }
                is Success -> {
                    promoAdapter.submitList(status())
                    binding.contentHome.piv.count = status().size
                }
                is Fail -> {
                    Toast.makeText(requireContext(), status.error.message, Toast.LENGTH_LONG).show()
                }
            }
            when (val status = state.cartCount) {
                Uninitialized -> {
                }
                is Loading -> {
                }
                is Success -> {
                    badgeDrawable.number = status()


//                    binding.contentHome.fab.viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
//                        @SuppressLint("RestrictedApi")
//                        override fun onGlobalLayout() {
//                            val badgeDrawable = BadgeDrawable.create(requireContext())
//                            badgeDrawable.number = status()
//                            //Important to change the position of the Badge
//                            badgeDrawable.horizontalOffset = 30
//                            badgeDrawable.verticalOffset = 20
//                            BadgeUtils.attachBadgeDrawable(badgeDrawable, binding.contentHome.fab, FrameLayout(requireContext()))
//                            binding.contentHome.fab.viewTreeObserver.removeOnGlobalLayoutListener(this)
//                        }
//                    })
                }
                is Fail -> {
                    Toast.makeText(requireContext(), status.error.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}

abstract class NoConstructorFragment : BaseMvRxFragment()