package com.shamsheev.wildberries.api.statistics.model

import java.time.LocalDate

interface StatisticsDto {
    fun getOrderDate(): LocalDate?
    fun getCount(): Int?
    fun getSubject(): String?
}