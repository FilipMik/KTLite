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

    val foodList: MutableLiveData<List<Food>> = MutableLiveData()
    val searchQuery: MutableLiveData<String> = MutableLiveData()

    fun getFoodList(query: String) {
        getFoodsUseCase.init(query).invoke().onEach { result ->
            when (result) {
                is Result.Loading -> {
                    result
                }
                is Result.Success -> {
                    result.data?.let {
                        foodList.value = it
                    }
                }
                is Result.Error -> {
                    result
                }
            }
        }.launchIn(viewModelScope)
    }
}
