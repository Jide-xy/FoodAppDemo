package com.example.fooddeliverydemo.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.fooddeliverydemo.model.Cart
import com.example.fooddeliverydemo.model.Food

data class CartWithFood(
        @Embedded
        val cart: Cart,
        @Relation(parentColumn = "foodId", entityColumn = "id")
        val food: Food
)
