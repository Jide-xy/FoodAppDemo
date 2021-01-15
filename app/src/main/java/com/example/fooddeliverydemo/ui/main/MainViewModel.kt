package com.example.fooddeliverydemo.ui.main

import com.airbnb.mvrx.*
import com.example.fooddeliverydemo.model.Food
import com.example.fooddeliverydemo.model.Promo
import com.example.fooddeliverydemo.model.relation.CategoryWithFood
import com.example.fooddeliverydemo.repository.Repository
import com.example.fooddeliverydemo.utils.BaseViewModel
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

data class HomeState(
        val categoryWithFood: Async<List<CategoryWithFood>> = Uninitialized,
        val promo: Async<List<Promo>> = Uninitialized,
        val cartCount: Async<Int> = Uninitialized
) : MvRxState

//@EntryPoint
class MainViewModel @AssistedInject constructor(
        @Assisted state: HomeState,
        private val repository: Repository
) : BaseViewModel<HomeState>(state) {

    init {
        getFoods()
        getPromos()
        repository.getTotalCartItemsCount().execute { copy(cartCount = it) }
    }

    fun getFoods() = repository.getAllFood().executeAsync {
        copy(categoryWithFood = it)
    }

    fun getPromos() = repository.getPromos().executeAsync {
        copy(promo = it)
    }

    fun addToCart(food: Food) {
        repository.addToCart(food)
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(state: HomeState): MainViewModel
    }

    companion object : MvRxViewModelFactory<MainViewModel, HomeState> {

        override fun create(viewModelContext: ViewModelContext, state: HomeState): MainViewModel {
            return (viewModelContext as FragmentViewModelContext).fragment<MainFragment>().viewModelFactory.create(state)
        }

    }
}