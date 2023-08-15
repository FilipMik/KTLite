package com.kaloricketabulky.ktlite.ui.foodlist

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FoodListModule {

    @Provides
    fun foodListView(fragment: FoodListFragment): FoodListView {
        return fragment
    }
}
