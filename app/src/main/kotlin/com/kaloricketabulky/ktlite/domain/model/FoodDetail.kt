package com.kaloricketabulky.ktlite.domain.model

data class FoodDetail(
    val guid: String,
    val name: String,
    val energy: String,
    val protein: String,
    val carbs: String,
    val fat: String,
    val list: List<Nutrient>
)

data class Nutrient(
    val nutrientNameId: Int,
    val nutrientValue: String? = null,
    val nutrientUnit: String
)
