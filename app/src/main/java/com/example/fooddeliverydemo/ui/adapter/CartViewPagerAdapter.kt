package com.example.fooddeliverydemo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliverydemo.databinding.ItemLayoutCartSliderBinding
import com.example.fooddeliverydemo.model.relation.CartWithFood

class CartViewPagerAdapter(var cart: List<CartWithFood> = emptyList()) : RecyclerView.Adapter<CartViewPagerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                ItemLayoutCartSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }


    inner class ViewHolder(private val binding: ItemLayoutCartSliderBinding) : RecyclerView.ViewHolder(binding.root) {
        private val adapter = CartAdapter()

        init {
            binding.cartRv.adapter = adapter
        }

        fun bind() {
            adapter.submitList(cart)
            binding.total.text = cart.sumByDouble {
                it.cart.quantity * it.food.price
            }.toString()
        }
    }

    override fun getItemCount(): Int {
        return 1
    }

}