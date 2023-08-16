package com.kaloricketabulky.ktlite.domain.usecase

import android.content.Context
import com.kaloricketabulky.ktlite.R
import com.kaloricketabulky.ktlite.domain.model.Food
import com.kaloricketabulky.ktlite.domain.repository.KTLiteRepository
import com.kaloricketabulky.ktlite.tools.Result
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetFoodsUseCase @Inject constructor(
    private val repository: KTLiteRepository,
    @ApplicationContext private val context: Context
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
        } catch(exception: RuntimeException) {
            emit(Result.Error(context.getString(R.string.nothing_found)))
        }
    }

    private fun formatEnergy(energyValue: String, energyUnit: String) : String {
        return String.format("%.2f", energyValue.toDouble()) + " " + energyUnit
    }
}
