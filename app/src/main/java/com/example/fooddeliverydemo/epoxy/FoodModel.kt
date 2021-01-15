package com.example.fooddeliverydemo.epoxy

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.example.fooddeliverydemo.R
import com.example.fooddeliverydemo.utils.KotlinEpoxyHolder
import com.google.android.material.button.MaterialButton

@EpoxyModelClass(layout = R.layout.item_layout_food_list)
abstract class FoodModel : EpoxyModelWithHolder<FoodModel.Holder>() {

    @EpoxyAttribute
    lateinit var imageUrl: String

    @EpoxyAttribute
    lateinit var name: String

    @EpoxyAttribute
    lateinit var description: String

    @EpoxyAttribute
    lateinit var others: String

    @EpoxyAttribute
    var price: Double = 0.0
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var clickListener: View.OnClickListener? = null

    override fun bind(holder: Holder) {
        with(holder) {
            nameTextView.text = name
            descriptionTextView.text = description
            othersTextView.text = others
            addToCartButton.text = price.toString()
            addToCartButton.setOnClickListener(clickListener)
            Glide.with(imageView).load(imageUrl).into(imageView)
        }
    }

    inner class Holder : KotlinEpoxyHolder() {
        val nameTextView by bind<TextView>(R.id.name)
        val descriptionTextView by bind<TextView>(R.id.description)
        val othersTextView by bind<TextView>(R.id.others)
        val imageView by bind<ImageView>(R.id.image)
        val addToCartButton by bind<MaterialButton>(R.id.add_to_cart)
    }
}