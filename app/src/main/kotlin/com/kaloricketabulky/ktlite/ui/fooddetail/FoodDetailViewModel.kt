package com.kaloricketabulky.ktlite.ui.fooddetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaloricketabulky.ktlite.domain.usecase.GetFoodDetailUseCase
import com.kaloricketabulky.ktlite.tools.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FoodDetailViewModel @Inject constructor(
    private val getFoodDetailUseCase: GetFoodDetailUseCase
) : ViewModel() {

    fun loadFoodDetail(guidFood: String) {
        getFoodDetailUseCase.init(guidFood).invoke().onEach { result ->
            when (result) {
                is Result.Loading -> {
                    result
                }
                is Result.Success -> {
                    result
                }
                is Result.Error -> {
                    result
                }
            }
        }.launchIn(viewModelScope)
    }
}
