package com.kaloricketabulky.ktlite.ui.foodlist

import com.kaloricketabulky.ktlite.domain.model.Food

interface FoodListView {

    fun onFoodClick(food: Food)
}
