package com.example.fooddeliverydemo.utils

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MvRxState
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel<S : MvRxState>(initialState: S) : BaseMvRxViewModel<S>(initialState, true) {

    fun <T : Async<*>> Observable<T>.executeAsync(
            stateReducer: S.(T) -> S
    ): Disposable {
        return subscribe { asyncData -> setState { stateReducer(asyncData) } }
                .disposeOnClear()
    }

}