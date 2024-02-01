package com.shamsheev.wildberries.api.statistics.service

import com.shamsheev.wildberries.api.statistics.model.ApiStatus
import com.shamsheev.wildberries.api.statistics.model.ApiType
import com.shamsheev.wildberries.api.statistics.ports.WbStatistics
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ApiRequestService(
    val wbStatistics: WbStatistics,
    val orderService: OrderService,
    val saleService: SaleService,
    val stockService: StockService,
    val incomeService: IncomeService,
    val apiRequestResultService: ApiRequestResultService,
) {

    @Value("\${timezone:4}")
    var timezone: Long = 4

    fun orders(startDateTime: LocalDateTime) {
        try {
            val fromTime = apiRequestResultService.getLastSuccessDateByApiType(ApiType.ORDERS)
            val ordersResult = wbStatistics.getOrders(fromTime.get().minusHours(timezone), 0)
            orderService.save(ordersResult)
            apiRequestResultService.save(startDateTime, ApiType.ORDERS, ApiStatus.SUCCESS, ordersResult.size)
        } catch (e: Exception) {
            log.error { "${e.message}: $e" }
            apiRequestResultService.save(startDateTime, ApiType.ORDERS, ApiStatus.ERROR, 0, e.message)
        }
    }

    fun sales(startDateTime: LocalDateTime) {
        try {
            val fromTime = apiRequestResultService.getLastSuccessDateByApiType(ApiType.SALES)
            val salesResult = wbStatistics.getSales(fromTime.get().minusHours(timezone), 0)
            saleService.save(salesResult)
            apiRequestResultService.save(startDateTime, ApiType.SALES, ApiStatus.SUCCESS, salesResult.size)
        } catch (e: Exception) {
            log.error { "${e.message}: $e" }
            apiRequestResultService.save(startDateTime, ApiType.SALES, ApiStatus.ERROR, 0, e.message)
        }
    }


    fun stocks(startDateTime: LocalDateTime) {
        try {
            val fromTime = apiRequestResultService.getLastSuccessDateByApiType(ApiType.STOCKS)
            val stocksResult = wbStatistics.getStocks(fromTime.get().minusHours(timezone))
            stockService.save(stocksResult)
            apiRequestResultService.save(startDateTime, ApiType.STOCKS, ApiStatus.SUCCESS, stocksResult.size)
        } catch (e: Exception) {
            log.error { "${e.message}: $e" }
            apiRequestResultService.save(startDateTime, ApiType.STOCKS, ApiStatus.ERROR, 0, e.message)
        }
    }


    fun incomes(startDateTime: LocalDateTime) {
        try {
            val fromTime = apiRequestResultService.getLastSuccessDateByApiType(ApiType.INCOMES)
            val incomesResult = wbStatistics.getIncomes(fromTime.get().minusHours(timezone))
            incomeService.save(incomesResult)
            apiRequestResultService.save(startDateTime, ApiType.INCOMES, ApiStatus.SUCCESS, incomesResult.size)
        } catch (e: Exception) {
            log.error { "${e.message}: $e" }
            apiRequestResultService.save(startDateTime, ApiType.STOCKS, ApiStatus.ERROR, 0, e.message)
        }
    }

    companion object {
        val log = KotlinLogging.logger {}
    }
}