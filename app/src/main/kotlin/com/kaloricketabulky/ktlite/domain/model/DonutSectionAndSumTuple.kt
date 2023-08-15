package com.kaloricketabulky.ktlite.domain.model

import app.futured.donut.DonutSection

data class DonutSectionAndSumTuple(
    val donutSections: List<DonutSection>,
    val sum: Float
)
