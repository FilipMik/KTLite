package com.kaloricketabulky.ktlite.ui.foodlist.adapter

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AdapterModule {

    @Provides
    fun provideFoodListAdapterFactory(): FoodListAdapterFactory {
        return FoodListAdapterFactoryImpl()
    }
}
