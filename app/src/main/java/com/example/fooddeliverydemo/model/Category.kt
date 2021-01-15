package com.example.fooddeliverydemo.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
        @PrimaryKey
        val id: Int,
        val name: String
)
