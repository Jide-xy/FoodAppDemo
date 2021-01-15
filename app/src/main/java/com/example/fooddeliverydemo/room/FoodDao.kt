package com.example.fooddeliverydemo.room

import androidx.room.*
import com.example.fooddeliverydemo.model.Category
import com.example.fooddeliverydemo.model.Food
import com.example.fooddeliverydemo.model.Promo
import com.example.fooddeliverydemo.model.relation.CategoryWithFood
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface FoodDao {

    @Transaction
    @Query("SELECT * FROM Category")
    fun getAllFoodAsObservable(): Observable<List<CategoryWithFood>>

    @Transaction
    @Query("SELECT * FROM Category")
    fun getAllFoodAsSingle(): Single<List<CategoryWithFood>>

    @Transaction
    @Query("SELECT * FROM Category")
    fun getAllFood(): List<CategoryWithFood>

    @Query("SELECT * FROM Promo")
    fun getPromosAsObservable(): Observable<List<Promo>>

    @Query("SELECT * FROM Promo")
    fun getPromosAsSingle(): Single<List<Promo>>

    @Query("SELECT * FROM Promo")
    fun getPromos(): List<Promo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(category: List<Category>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFood(food: List<Food>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPromo(promo: List<Promo>)

    @Transaction
    fun saveCategoriesWithFood(food: List<CategoryWithFood>) {
        insertCategory(food.map { it.category })
        insertFood(food.flatMap { it.food })
    }
}