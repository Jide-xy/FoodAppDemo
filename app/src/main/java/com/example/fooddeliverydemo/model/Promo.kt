package com.example.fooddeliverydemo.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Promo(
        @PrimaryKey
        val id: Int,
        val imageUrl: String,
        val promoUrl: String
)
