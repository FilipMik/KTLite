package com.kaloricketabulky.ktlite.domain.model

data class Food(
    val guid: String,
    val name: String,
    val photoThumb: String?,
    val energy: Energy
)

data class Energy(
    val jednotka: String,
    val value: String
)

fun Energy.toString() = "$value $jednotka"
