package com.kaloricketabulky.ktlite.data.repository

import com.kaloricketabulky.ktlite.data.remote.KalorickeTabulkyApi
import com.kaloricketabulky.ktlite.data.remote.dto.FoodDetailDto
import com.kaloricketabulky.ktlite.data.remote.dto.SearchResponseDto
import com.kaloricketabulky.ktlite.domain.repository.KTLiteRepository

class KTLiteRepositoryImpl(
    private val kalorickeTabulkyApi: KalorickeTabulkyApi
) : KTLiteRepository {
    override suspend fun getFoodListResponse(searchQuery: String) : SearchResponseDto =
        kalorickeTabulkyApi.getFoods(searchQuery)

    override suspend fun getFoodDetail(foodId: String) : FoodDetailDto =
        kalorickeTabulkyApi.getFoodDetail(foodId)
}
