package com.kaloricketabulky.ktlite.domain.repository

import com.kaloricketabulky.ktlite.data.remote.dto.FoodDetailDto
import com.kaloricketabulky.ktlite.data.remote.dto.SearchResponseDto

interface KTLiteRepository {

    suspend fun getFoodListResponse(searchQuery: String) : SearchResponseDto

    suspend fun getFoodDetail(foodId: String) : FoodDetailDto
}
