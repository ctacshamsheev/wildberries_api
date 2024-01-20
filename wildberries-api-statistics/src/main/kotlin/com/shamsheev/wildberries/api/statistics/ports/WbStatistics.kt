package com.shamsheev.wildberries.api.statistics.ports

import openapi.wildberries.ru.statistics.models.*
import java.time.LocalDate
import java.time.LocalDateTime

interface WbStatistics {

    // TODO return model
    fun getIncomes(dateFrom: LocalDateTime): List<IncomesItem>

    fun getStocks(dateFrom: LocalDateTime): List<StocksItem>

    fun getOrders(dateFrom: LocalDateTime, flag: Int = 0): List<OrdersItem>

    fun getSales(dateFrom: LocalDateTime, flag: Int = 0): List<SalesItem>

    fun getReport(dateFrom: LocalDate, dateTo: LocalDate): List<DetailReportItem>
}