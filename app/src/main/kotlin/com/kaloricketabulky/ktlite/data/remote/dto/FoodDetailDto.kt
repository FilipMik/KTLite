package com.kaloricketabulky.ktlite.data.remote.dto

import com.kaloricketabulky.ktlite.domain.model.FoodDetail

data class FoodDetailDto (
    val id: String
)

fun FoodDetailDto.toFoodDetail() : FoodDetail = FoodDetail(id)
