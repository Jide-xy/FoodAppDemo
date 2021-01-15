package com.example.fooddeliverydemo.repository

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Success
import com.example.fooddeliverydemo.model.Food
import com.example.fooddeliverydemo.model.Promo
import com.example.fooddeliverydemo.model.relation.CartWithFood
import com.example.fooddeliverydemo.model.relation.CategoryWithFood
import com.example.fooddeliverydemo.network.NetworkSimulator
import com.example.fooddeliverydemo.room.CartDao
import com.example.fooddeliverydemo.room.FoodDao
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
        private val foodService: NetworkSimulator,
        private val foodDao: FoodDao,
        private val cartDao: CartDao
) : Repository {

    override fun getAllFood(): Observable<Async<List<CategoryWithFood>>> {
        return foodService.getAllFood()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap<Async<List<CategoryWithFood>>> {
                    foodDao.saveCategoriesWithFood(it)
                    foodDao.getAllFoodAsObservable().map { food -> Success(food) }
                }
//                .observeOn(Schedulers.io())
//                .startWith (
//                    Loading(foodDao.getAllFood())
////                foodDao.getAllFoodAsObservable().map {
////                    food -> Loading(food) }
//                )
                .observeOn(Schedulers.io())
                .onErrorReturn {
                    return@onErrorReturn Fail(it, foodDao.getAllFood())
                }
                .observeOn(Schedulers.io())
//            .subscribeOn(Schedulers.io())
//            .subscribeOn(Schedulers.io())
    }

    override fun getPromos(): Observable<Async<List<Promo>>> {
        return foodService.getPromos()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap<Async<List<Promo>>> {
                    foodDao.insertPromo(it)
                    foodDao.getPromosAsObservable().map { promo -> Success(promo) }
                }
//                .observeOn(Schedulers.io())
//                .startWith (
//                    Loading(foodDao.getPromos())
////                foodDao.getPromosAsSingle().map { promo -> Loading(promo) }
//                )
                .observeOn(Schedulers.io())
                .onErrorReturn { return@onErrorReturn Fail(it, foodDao.getPromos()) }


    }

    override fun addToCart(food: Food) {
        Completable.fromAction { cartDao.addToCart(food) }.subscribeOn(Schedulers.io())
                .subscribe()
    }

    override fun getTotalCartItemsCount(): Observable<Int> = cartDao.getTotalCartItemsCount()

    override fun getAllCartItems(): Observable<List<CartWithFood>> = cartDao.getCartItems()
}