package com.kaloricketabulky.ktlite.ui.fooddetail

import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.futured.donut.DonutSection
import com.kaloricketabulky.ktlite.domain.model.FoodDetail
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

    val energyValue = MutableLiveData<String>()
    val donutSections = MutableLiveData<List<DonutSection>>()

    fun loadFoodDetail(guidFood: String) {
        getFoodDetailUseCase.init(guidFood).invoke().onEach { result ->
            when (result) {
                is Result.Loading -> {
                    result
                }
                is Result.Success -> {
                    result.data?.let {
                        donutSections.value = createDonutSections(it)
                    }
                }
                is Result.Error -> {
                    result
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun createDonutSections(foodDetail: FoodDetail): List<DonutSection> = listOf(
            DonutSection(
                name = "proteins",
                color = Color.parseColor("#00D5FF"),
                amount = foodDetail.protein.toFloat()
            ),
            DonutSection(
                name = "carbs",
                color = Color.parseColor("#FBE81D"),
                amount = foodDetail.carbs.toFloat()
            ),
            DonutSection(
                name = "fats",
                color = Color.parseColor("#FB1D32"),
                amount = foodDetail.fat.toFloat()
            )
        )
}
