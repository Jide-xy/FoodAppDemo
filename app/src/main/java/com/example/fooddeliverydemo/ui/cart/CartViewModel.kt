package com.example.fooddeliverydemo.ui.cart

import com.airbnb.mvrx.*
import com.example.fooddeliverydemo.model.relation.CartWithFood
import com.example.fooddeliverydemo.repository.Repository
import com.example.fooddeliverydemo.utils.BaseViewModel
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

data class CartState(
        val cart: Async<List<CartWithFood>> = Uninitialized
) : MvRxState

class CartViewModel @AssistedInject constructor(
        @Assisted state: CartState,
        private val repository: Repository
) : BaseViewModel<CartState>(state) {

    init {
        repository.getAllCartItems().execute { copy(cart = it) }
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(state: CartState): CartViewModel
    }

    companion object : MvRxViewModelFactory<CartViewModel, CartState> {

        override fun create(viewModelContext: ViewModelContext, state: CartState): CartViewModel {
            return (viewModelContext as FragmentViewModelContext).fragment<CartFragment>().viewModelFactory.create(state)
        }

    }
}