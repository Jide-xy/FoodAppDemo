package com.example.fooddeliverydemo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliverydemo.databinding.ItemLayoutFoodCategoryBinding
import com.example.fooddeliverydemo.epoxy.FoodController
import com.example.fooddeliverydemo.model.Food
import com.example.fooddeliverydemo.model.relation.CategoryWithFood

class CategoryViewPagerAdapter(private val onSelect: (Food) -> Unit) : ListAdapter<CategoryWithFood, CategoryViewPagerAdapter.ViewHolder>(
        DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                ItemLayoutFoodCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    fun getCategory(position: Int) = getItem(position).category

    inner class ViewHolder(binding: ItemLayoutFoodCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

        private val controller = FoodController {
            onSelect(it)
        }

        init {
            binding.foodRv.adapter = controller.adapter
        }

        fun bind() {
            controller.setData(getItem(adapterPosition).food)
        }
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<CategoryWithFood>() {
            override fun areItemsTheSame(
                    oldItem: CategoryWithFood,
                    newItem: CategoryWithFood
            ): Boolean {
                return oldItem.category.id == newItem.category.id
            }

            override fun areContentsTheSame(
                    oldItem: CategoryWithFood,
                    newItem: CategoryWithFood
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}