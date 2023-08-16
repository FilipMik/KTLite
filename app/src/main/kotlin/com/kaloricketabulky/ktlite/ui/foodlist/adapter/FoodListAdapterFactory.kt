package com.kaloricketabulky.ktlite.ui.foodlist.adapter

import com.kaloricketabulky.ktlite.ui.foodlist.FoodListView

interface FoodListAdapterFactory {
    fun create(foodListView: FoodListView): FoodListAdapter
}
