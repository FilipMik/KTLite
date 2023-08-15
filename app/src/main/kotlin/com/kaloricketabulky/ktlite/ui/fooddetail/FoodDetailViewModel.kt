package com.kaloricketabulky.ktlite.ui.fooddetail

import androidx.lifecycle.ViewModel
import com.kaloricketabulky.ktlite.domain.usecase.GetFoodDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FoodDetailViewModel @Inject constructor(
    private val getFoodDetailUseCase: GetFoodDetailUseCase
) :ViewModel() {
}
