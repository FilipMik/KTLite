package com.kaloricketabulky.ktlite.domain.usecase

import com.kaloricketabulky.ktlite.domain.model.Energy
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

    operator fun invoke(): Flow<Result<List<Food>>> = flow {
        try {
            emit(Result.Loading())
            val foodList = repository.getFoodList("a").map {
                Food(it.guid, it.nazev, it.fotoThumb, Energy(it.energie.jednotka, it.energie.value))
            }
            emit(Result.Success(foodList))
        } catch(exception: HttpException) {
            emit(Result.Error(exception.localizedMessage ?: "Unexcepted error"))
        } catch(exception: IOException) {
            emit(Result.Error(exception.localizedMessage ?: "Network error"))
        }
    }
}
