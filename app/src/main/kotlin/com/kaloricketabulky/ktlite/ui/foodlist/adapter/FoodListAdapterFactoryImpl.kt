package com.kaloricketabulky.ktlite.ui.foodlist.adapter

import com.kaloricketabulky.ktlite.ui.foodlist.FoodListView
import javax.inject.Inject

class FoodListAdapterFactoryImpl @Inject constructor() : FoodListAdapterFactory {

    override fun create(foodListView: FoodListView): FoodListAdapter {
        return FoodListAdapter(foodListView)
    }
}
