package com.example.fooddeliverydemo.repository

import com.airbnb.mvrx.Async
import com.example.fooddeliverydemo.model.Food
import com.example.fooddeliverydemo.model.Promo
import com.example.fooddeliverydemo.model.relation.CartWithFood
import com.example.fooddeliverydemo.model.relation.CategoryWithFood
import io.reactivex.Observable

interface Repository {
    fun getPromos(): Observable<Async<List<Promo>>>
    fun getAllFood(): Observable<Async<List<CategoryWithFood>>>
    fun addToCart(food: Food)
    fun getTotalCartItemsCount(): Observable<Int>
    fun getAllCartItems(): Observable<List<CartWithFood>>
}