package com.kaloricketabulky.ktlite.data.repository

import com.kaloricketabulky.ktlite.data.remote.KalorickeTabulkyApi
import com.kaloricketabulky.ktlite.domain.repository.KTLiteRepository

class KTLiteRepositoryImpl(
    private val kalorickeTabulkyApi: KalorickeTabulkyApi
) : KTLiteRepository {
    override suspend fun getFoodList(searchQuery: String) {
        kalorickeTabulkyApi.getFoodList(searchQuery)
    }

    override suspend fun getFoodDetail(foodId: String) {
        kalorickeTabulkyApi.getFoodDetail(foodId)
    }
}
