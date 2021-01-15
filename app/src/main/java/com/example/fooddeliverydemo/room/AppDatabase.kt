package com.example.fooddeliverydemo.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fooddeliverydemo.model.Cart
import com.example.fooddeliverydemo.model.Category
import com.example.fooddeliverydemo.model.Food
import com.example.fooddeliverydemo.model.Promo

@Database(
        entities = [Food::class, Category::class, Cart::class, Promo::class],
        version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDao
    abstract fun cartDao(): CartDao


    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(
                context: Context
        ): AppDatabase =
                INSTANCE
                        ?: synchronized(this) {
                            INSTANCE
                                    ?: buildDatabase(context)
                                            .also {
                                                INSTANCE = it
                                            }
                        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "FoodDB"
                ).build()

    }


}