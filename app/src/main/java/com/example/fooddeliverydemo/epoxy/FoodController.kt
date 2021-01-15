package com.example.fooddeliverydemo.epoxy

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Handler
import android.widget.Button
import androidx.core.view.ViewCompat
import com.airbnb.epoxy.AutoModel
import com.airbnb.epoxy.TypedEpoxyController
import com.example.fooddeliverydemo.model.Food

class FoodController(private val onSelect: (Food) -> Unit) : TypedEpoxyController<List<Food>>() {
    @AutoModel
    lateinit var foodModel: FoodModel_

    override fun buildModels(foods: List<Food>) {
        foods.forEach {
            food {
                id("food")
                name(it.name)
                description(it.description)
                others("${it.weight} grams ${it.quantityAvailable} pieces")
                imageUrl(it.imageUrl)
                price(it.price)
                clickListener { model, parentView, clickedView, position ->
                    onSelect(it)
                    val prevText = model.price()
                    val prevColor = ViewCompat.getBackgroundTintList(clickedView)
                    ViewCompat.setBackgroundTintList(clickedView, ColorStateList.valueOf(Color.GREEN))
                    (clickedView as? Button)?.text = "Added +1"
                    Handler().postDelayed({
                        (clickedView as? Button)?.text = prevText.toString()
                        ViewCompat.setBackgroundTintList(clickedView, prevColor)
                    }, 500)
                }
            }
        }
    }

}