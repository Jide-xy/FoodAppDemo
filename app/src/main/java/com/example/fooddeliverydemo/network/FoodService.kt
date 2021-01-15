package com.example.fooddeliverydemo.network

import com.example.fooddeliverydemo.model.Promo
import com.example.fooddeliverydemo.model.relation.CategoryWithFood
import io.reactivex.Observable
import retrofit2.http.GET

interface FoodService {

    @GET("/foods")
    fun getAllFood(): Observable<List<CategoryWithFood>>

    @GET("/promos")
    fun getPromos(): Observable<List<Promo>>
}