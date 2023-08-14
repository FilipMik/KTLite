package com.kaloricketabulky.ktlite.domain.repository

import com.kaloricketabulky.ktlite.data.remote.dto.FoodDetailDto
import com.kaloricketabulky.ktlite.data.remote.dto.FoodDto

interface KTLiteRepository {

    suspend fun getFoodList(searchQuery: String) : List<FoodDto>

    suspend fun getFoodDetail(foodId: String) : FoodDetailDto
}
