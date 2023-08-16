package com.kaloricketabulky.ktlite.tools

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kaloricketabulky.ktlite.R
import com.kaloricketabulky.ktlite.domain.model.Nutrient

object BindingAdapters {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(view: ImageView, imageUrl: String?) {
        when (imageUrl) {
            null -> {
                Glide.with(view.context)
                    .load(R.drawable.ic_food_plate)
                    .apply(RequestOptions.centerCropTransform())
                    .into(view)
            }
            else -> {
                Glide.with(view.context)
                    .load(imageUrl)
                    .apply(RequestOptions.centerCropTransform())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_food_plate))
                    .into(view)
            }
        }
    }

    @BindingAdapter("onFoodItemClick")
    @JvmStatic
    fun setOnFoodItemClick(view: View, listener: View.OnClickListener) {
        view.setOnClickListener(listener)
    }

    @BindingAdapter("setNutrientValue")
    @JvmStatic
    fun setNutrientValue(textView: TextView, nutrient: Nutrient) {
        if (nutrient.nutrientValue.isNullOrEmpty()) {
            textView.text = textView.context.getString(R.string.dash)
        } else {
            textView.text = textView.context.getString(
                R.string.formatted_nutrient_with_unit,
                nutrient.nutrientValue,
                nutrient.nutrientUnit
            )
        }
    }

    @BindingConversion
    @JvmStatic
    fun visibility(visibility: Boolean) = if (visibility) View.VISIBLE else View.GONE
}
