package com.example.fooddeliverydemo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fooddeliverydemo.databinding.ItemLayoutCartBinding
import com.example.fooddeliverydemo.model.relation.CartWithFood

class CartAdapter : ListAdapter<CartWithFood, CartAdapter.ViewHolder>(
        DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                ItemLayoutCartBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    inner class ViewHolder(private val binding: ItemLayoutCartBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            with(getItem(adapterPosition)) {
                binding.amount.text = "${food.price} USD"
                binding.name.text = food.name
                Glide.with(binding.image).load(food.imageUrl).into(binding.image)
            }

        }
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<CartWithFood>() {
            override fun areItemsTheSame(
                    oldItem: CartWithFood,
                    newItem: CartWithFood
            ): Boolean {
                return oldItem.cart.foodId == newItem.cart.foodId
            }

            override fun areContentsTheSame(
                    oldItem: CartWithFood,
                    newItem: CartWithFood
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}