package com.kaloricketabulky.ktlite.domain.repository

interface KTLiteRepository {

    suspend fun getFoodList(searchQuery: String)

    suspend fun getFoodDetail(foodId: String)
}
