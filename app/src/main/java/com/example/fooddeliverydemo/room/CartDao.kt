package com.example.fooddeliverydemo.room

import androidx.room.*
import com.example.fooddeliverydemo.model.Cart
import com.example.fooddeliverydemo.model.Food
import com.example.fooddeliverydemo.model.relation.CartWithFood
import io.reactivex.Observable

@Dao
interface CartDao {

    @Query("SELECT quantityAvailable FROM food WHERE id = :id")
    fun getQuantityLeft(id: Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFood(food: Food)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToCart(cart: Cart)

    @Query("SELECT * FROM cart WHERE foodId = :id")
    fun getCartItem(id: Int): Cart?

    @Query("SELECT SUM(quantity) AS count FROM cart ")
    fun getTotalCartItemsCount(): Observable<Int>

    @Query("SELECT * FROM cart")
    fun getCartItems(): Observable<List<CartWithFood>>

    @Transaction
    fun addToCart(food: Food) {
        val quantityLeft = getQuantityLeft(food.id)
        if (quantityLeft <= 0) return
        insertFood(food.copy(quantityAvailable = quantityLeft - 1))
        addToCart(Cart(food.id, (getCartItem(food.id)?.quantity ?: 0) + 1))
    }
}