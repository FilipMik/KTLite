package com.kaloricketabulky.ktlite.ui.foodlist

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FoodListModule {

    @Provides
    fun clipsView(fragment: FoodListFragment): FoodListView = fragment
}
