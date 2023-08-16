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
