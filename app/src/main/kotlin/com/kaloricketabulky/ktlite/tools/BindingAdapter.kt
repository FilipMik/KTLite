package com.kaloricketabulky.ktlite.tools

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kaloricketabulky.ktlite.R

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
}
