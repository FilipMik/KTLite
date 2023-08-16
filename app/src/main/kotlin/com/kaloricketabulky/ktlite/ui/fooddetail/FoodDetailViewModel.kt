package com.kaloricketabulky.ktlite.ui.fooddetail

import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.futured.donut.DonutSection
import com.kaloricketabulky.ktlite.domain.model.DonutSectionAndSumTuple
import com.kaloricketabulky.ktlite.domain.model.FoodDetail
import com.kaloricketabulky.ktlite.domain.model.Nutrient
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

    // ViewState could be created to hold all values, instead of ViewModel
    val energyValue = MutableLiveData("0kcal")

    val proteins = MutableLiveData("0g")
    val carbs = MutableLiveData("0g")
    val fats = MutableLiveData("0g")

    val proteinsPercent = MutableLiveData("0%")
    val carbsPercent = MutableLiveData("0%")
    val fatsPercent = MutableLiveData("0%")

    val donutSectionsAndSumTuple = MutableLiveData<DonutSectionAndSumTuple>()

    val nutrients: MutableLiveData<List<Nutrient>> = MutableLiveData()

    val isLoading = MutableLiveData(true)
    val isError = MutableLiveData(false)
    val isContent = MutableLiveData(false)
    val errorMessage = MutableLiveData("")

    fun loadFoodDetail(guidFood: String) {
        getFoodDetailUseCase.init(guidFood).invoke().onEach { result ->
            when (result) {
                is Result.Loading -> {
                    isError.value = false
                    isLoading.value = true
                }
                is Result.Success -> {
                    isLoading.value = false
                    isContent.value = true

                    result.data?.let {
                        donutSectionsAndSumTuple.value = createDonutSectionAndSumTuple(it)
                        energyValue.value = it.energy
                        setMacros(it)
                        calculatePercents(it)
                        nutrients.value = it.list
                    }
                }
                is Result.Error -> {
                    errorMessage.value = result.message
                    isError.value = true
                    isLoading.value = false
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun setMacros(foodDetail: FoodDetail) {
        proteins.value = foodDetail.protein
        carbs.value = foodDetail.carbs
        fats.value = foodDetail.fat
    }

    private fun calculatePercents(foodDetail: FoodDetail) {
        foodDetail.apply {
            val sum = protein.toFloat() + carbs.toFloat() + fat.toFloat()
            proteinsPercent.value = String.format("%.2f", (100 / sum) * protein.toFloat())
            carbsPercent.value = String.format("%.2f", (100 / sum) * carbs.toFloat())
            fatsPercent.value = String.format("%.2f", (100 / sum) * fat.toFloat())
        }
    }

    private fun createDonutSectionAndSumTuple(foodDetail: FoodDetail): DonutSectionAndSumTuple {
        val sectionList = listOf(
            DonutSection(
                name = "proteins",
                color = Color.parseColor("#00ddff"),
                amount = foodDetail.protein.toFloat()
            ),
            DonutSection(
                name = "fats",
                color = Color.parseColor("#f2021a"),
                amount = foodDetail.fat.toFloat()
            ),
            DonutSection(
                name = "carbs",
                color = Color.parseColor("#ffc403"),
                amount = foodDetail.carbs.toFloat()
            )
        )

        var sum = 0f

        sectionList.forEach {
            sum += it.amount
        }

        return DonutSectionAndSumTuple(sectionList, sum)
    }
}
