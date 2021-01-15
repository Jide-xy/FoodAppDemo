package com.example.fooddeliverydemo.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = Food::class, parentColumns = ["id"], childColumns = ["foodId"])])
data class Cart(
        @PrimaryKey
        val foodId: Int,
        val quantity: Int
)
