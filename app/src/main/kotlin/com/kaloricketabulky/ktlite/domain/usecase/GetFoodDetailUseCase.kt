package com.kaloricketabulky.ktlite.domain.usecase

import com.kaloricketabulky.ktlite.R
import com.kaloricketabulky.ktlite.domain.model.FoodDetail
import com.kaloricketabulky.ktlite.domain.model.Nutrient
import com.kaloricketabulky.ktlite.domain.repository.KTLiteRepository
import com.kaloricketabulky.ktlite.tools.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

class GetFoodDetailUseCase @Inject constructor(
    private val repository: KTLiteRepository
) {

    private var guidFood: String = ""
    private val defaultMultiplier = 100

    fun init(guidFood: String) = apply {
        this.guidFood = guidFood
    }

    operator fun invoke(): Flow<Result<FoodDetail>> = flow {
        try {
            emit(Result.Loading())

            val foodDetail = repository.getFoodDetail(guidFood).run {
                FoodDetail(
                    guidPotravina,
                    nazev,
                    hodnoty.energie.hodnota.multiplyBy(defaultMultiplier) + hodnoty.energie.jednotka,
                    hodnoty.bilkoviny.hodnota.multiplyBy(defaultMultiplier),
                    hodnoty.sacharidy.hodnota.multiplyBy(defaultMultiplier),
                    hodnoty.tuky.hodnota.multiplyBy(defaultMultiplier),
                    listOf(
                        // Cukry
                        // Vlaknina
                        // Nasytene mastne kyseliny
                        // Polynenasycene mastne kyseliny
                        // Mononenasytene mastne kyseliny
                        // Transmastné kyseliny
                        // Cholesterol
                        Nutrient(
                            R.string.cukry,
                            hodnoty.cukry.hodnota, hodnoty.cukry.jednotka),
                        Nutrient(
                            R.string.vlaknina,
                            hodnoty.vlaknina.hodnota, hodnoty.vlaknina.jednotka),
                        Nutrient(
                            R.string.nasytene,
                            hodnoty.nasyceneMastneKyseliny.hodnota, hodnoty.nasyceneMastneKyseliny.jednotka),
                        Nutrient(
                            R.string.poly,
                            hodnoty.polynenasycene.hodnota, hodnoty.polynenasycene.jednotka),
                        Nutrient(
                            R.string.mono, hodnoty.mononenasycene.hodnota, hodnoty.mononenasycene.jednotka),
                        Nutrient(
                            R.string.trans, hodnoty.transmastneKyseliny.hodnota, hodnoty.transmastneKyseliny.jednotka),
                        Nutrient(
                            R.string.cholesterol, hodnoty.cholesterol.hodnota, hodnoty.cholesterol.jednotka),
                    )
                )
            }

            emit(Result.Success(foodDetail))
        } catch(exception: HttpException) {
            emit(Result.Error(exception.localizedMessage ?: "Unexcepted error"))
        } catch(exception: IOException) {
            emit(Result.Error(exception.localizedMessage ?: "Network error"))
        }
    }

    private fun String.multiplyBy(multiplier: Int) : String {
        val originalFloat = this.toFloat() * multiplier
        return String.format("%.2f", originalFloat)
    }
}
