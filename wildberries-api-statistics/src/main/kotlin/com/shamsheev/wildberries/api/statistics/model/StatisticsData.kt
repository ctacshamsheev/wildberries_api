package com.shamsheev.wildberries.api.statistics.model

import java.time.LocalDate

data class StatisticsData(
    val headers: List<String>,
    val dates: List<LocalDate>,
    val table: Array<Array<Int>>,
)
