package com.shamsheev.wildberries.api.statistics.ports

import com.shamsheev.wildberries.api.statistics.model.Order
import com.shamsheev.wildberries.api.statistics.model.Sale
import openapi.wildberries.ru.statistics.models.DetailReportItem
import openapi.wildberries.ru.statistics.models.IncomesItem
import openapi.wildberries.ru.statistics.models.StocksItem
import java.time.LocalDate
import java.time.LocalDateTime

interface WbStatistics {

    fun getIncomes(dateFrom: LocalDateTime): List<IncomesItem>

    fun getStocks(dateFrom: LocalDateTime): List<StocksItem>

    fun getOrders(dateFrom: LocalDateTime, flag: Int = 0): List<Order>

    fun getSales(dateFrom: LocalDateTime, flag: Int = 0): List<Sale>

    fun getReport(dateFrom: LocalDate, dateTo: LocalDate): List<DetailReportItem>
}