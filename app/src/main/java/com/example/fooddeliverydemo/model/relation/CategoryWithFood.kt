package com.example.fooddeliverydemo.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.fooddeliverydemo.model.Category
import com.example.fooddeliverydemo.model.Food

data class CategoryWithFood(
        @Embedded
        val category: Category,
        @Relation(parentColumn = "id", entityColumn = "categoryId")
        val food: List<Food>
)
