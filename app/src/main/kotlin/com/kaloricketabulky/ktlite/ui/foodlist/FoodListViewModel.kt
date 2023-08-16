package com.kaloricketabulky.ktlite.ui.foodlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaloricketabulky.ktlite.domain.model.Food
import com.kaloricketabulky.ktlite.domain.usecase.GetFoodsUseCase
import com.kaloricketabulky.ktlite.tools.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FoodListViewModel @Inject constructor(
    private val getFoodsUseCase: GetFoodsUseCase
) : ViewModel() {

    val foodList = MutableLiveData<List<Food>>()
    val searchQuery = MutableLiveData<String>()

    val isLoading = MutableLiveData(false)
    val isError = MutableLiveData(false)
    val isEmpty = MutableLiveData(true)
    val errorMessage = MutableLiveData("")

    fun getFoodList(query: String) {
        getFoodsUseCase.init(query).invoke().onEach { result ->
            when (result) {
                is Result.Loading -> {
                    isLoading.value = true
                    isError.value = false
                    isEmpty.value = false
                    errorMessage.value = ""
                }
                is Result.Success -> {
                    isLoading.value = false
                    isError.value = false
                    isEmpty.value = false
                    result.data?.let {
                        foodList.value = it
                    }
                }
                is Result.Error -> {
                    errorMessage.value = result.message
                    isError.value = true
                    isLoading.value = false
                    isEmpty.value = false
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEmptyQuery() {
        foodList.value = listOf()
        isError.value = false
        isEmpty.value = true
    }
}
