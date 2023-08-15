package com.kaloricketabulky.ktlite.domain.usecase

import com.kaloricketabulky.ktlite.data.remote.dto.FoodDetailDto
import com.kaloricketabulky.ktlite.domain.model.Food
import com.kaloricketabulky.ktlite.domain.repository.KTLiteRepository
import com.kaloricketabulky.ktlite.tools.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetFoodDetailUseCase @Inject constructor(
    private val repository: KTLiteRepository
) {

    private var guidFood: String = ""

    fun init(guidFood: String) = apply {
        this.guidFood = guidFood
    }

    operator fun invoke(): Flow<Result<FoodDetailDto>> = flow {
        try {
            emit(Result.Loading())

            emit(Result.Success(repository.getFoodDetail(guidFood)))
        } catch(exception: HttpException) {
            emit(Result.Error(exception.localizedMessage ?: "Unexcepted error"))
        } catch(exception: IOException) {
            emit(Result.Error(exception.localizedMessage ?: "Network error"))
        }
    }
}
