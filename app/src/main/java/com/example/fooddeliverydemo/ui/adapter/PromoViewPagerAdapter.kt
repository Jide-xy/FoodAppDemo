package com.example.fooddeliverydemo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fooddeliverydemo.databinding.ItemLayoutPromoBinding
import com.example.fooddeliverydemo.model.Promo

class PromoViewPagerAdapter : ListAdapter<Promo, PromoViewPagerAdapter.ViewHolder>(
        DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                ItemLayoutPromoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    inner class ViewHolder(private val binding: ItemLayoutPromoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            Glide.with(binding.promoImage).load(getItem(adapterPosition).imageUrl).into(binding.promoImage)
        }
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Promo>() {
            override fun areItemsTheSame(
                    oldItem: Promo,
                    newItem: Promo
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                    oldItem: Promo,
                    newItem: Promo
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}