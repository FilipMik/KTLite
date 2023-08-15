package com.kaloricketabulky.ktlite.domain.usecase

import com.kaloricketabulky.ktlite.domain.model.Food
import com.kaloricketabulky.ktlite.domain.repository.KTLiteRepository
import com.kaloricketabulky.ktlite.tools.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetFoodsUseCase @Inject constructor(
    private val repository: KTLiteRepository
) {

    private var query: String = ""

    fun init(query: String) = apply {
        this.query = query
    }

    operator fun invoke(): Flow<Result<List<Food>>> = flow {
        try {
            emit(Result.Loading())

            val searchResponse = repository.getFoodListResponse(query)
            val energyUnit = searchResponse.unit

            val foodList = searchResponse.foods.map {
                Food(
                    it.guid,
                    it.name,
                    it.thumbnailPhoto,
                    formatEnergy(it.energy.energyValue, energyUnit)
                )
            }

            emit(Result.Success(foodList))
        } catch(exception: HttpException) {
            emit(Result.Error(exception.localizedMessage ?: "Unexcepted error"))
        } catch(exception: IOException) {
            emit(Result.Error(exception.localizedMessage ?: "Network error"))
        }
    }

    private fun formatEnergy(energyValue: String, energyUnit: String) : String {
        return String.format("%.2f", energyValue.toDouble()) + " " + energyUnit
    }
}
