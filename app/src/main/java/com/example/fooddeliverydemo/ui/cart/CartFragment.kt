package com.example.fooddeliverydemo.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.example.fooddeliverydemo.R
import com.example.fooddeliverydemo.databinding.CartFragmentBinding
import com.example.fooddeliverydemo.ui.adapter.CartViewPagerAdapter
import com.example.fooddeliverydemo.ui.main.NoConstructorFragment
import com.example.fooddeliverydemo.utils.viewbinding.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment : NoConstructorFragment() {

    @Inject
    lateinit var viewModelFactory: CartViewModel.Factory

    private val viewModel: CartViewModel by fragmentViewModel()

    private val binding by viewBinding(CartFragmentBinding::bind)

    private val adapter = CartViewPagerAdapter()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cart_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.categoryViewPager.adapter = adapter
        TabLayoutMediator(binding.categoryTabs, binding.categoryViewPager) { tab, position ->
            tab.text = "Cart"
        }
    }

    override fun invalidate() {
        withState(viewModel) {
            when (it.cart) {
                is Success -> {
                    adapter.cart = it.cart().orEmpty()
                    adapter.notifyDataSetChanged()
                }
                is Fail -> {
                    Toast.makeText(requireContext(), it.cart.error.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }


}