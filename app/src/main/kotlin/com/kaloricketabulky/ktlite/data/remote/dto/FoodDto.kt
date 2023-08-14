package com.kaloricketabulky.ktlite.data.remote.dto

import com.kaloricketabulky.ktlite.domain.model.Food

data class FoodDto(
    val id: String
)

fun FoodDto.toFood() : Food = Food(id)
