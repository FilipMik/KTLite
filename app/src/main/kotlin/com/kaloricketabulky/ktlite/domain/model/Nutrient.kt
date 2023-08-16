package com.kaloricketabulky.ktlite.domain.model

data class Nutrient(
    val nutrientNameId: Int,
    val nutrientValue: String? = null,
    val nutrientUnit: String
)
