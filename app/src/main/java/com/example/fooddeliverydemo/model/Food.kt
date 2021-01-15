package com.example.fooddeliverydemo.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = Category::class, parentColumns = ["id"], childColumns = ["categoryId"])])
data class Food(
        @PrimaryKey
        val id: Int,
        val name: String,
        val description: String,
        val price: Double,
        val quantityAvailable: Int,
        val weight: Double,
        val imageUrl: String,
        val categoryId: Int
)
