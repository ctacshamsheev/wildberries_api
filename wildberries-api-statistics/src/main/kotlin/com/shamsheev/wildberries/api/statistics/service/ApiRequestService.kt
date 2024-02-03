package com.shamsheev.wildberries.api.statistics.service

import com.shamsheev.wildberries.api.statistics.model.*
import com.shamsheev.wildberries.api.statistics.ports.WbStatistics
import mu.KotlinLogging
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


    fun orders(startDateTime: LocalDateTime, fromTime: LocalDateTime): List<Order> {
        return try {
            val ordersResult = wbStatistics.getOrders(fromTime, 0)
            orderService.save(ordersResult)
            apiRequestResultService.save(startDateTime, ApiType.ORDERS, ApiStatus.SUCCESS, ordersResult.size, fromTime)
            ordersResult
        } catch (e: Exception) {
            log.error { "${e.message}: $e" }
            apiRequestResultService.save(startDateTime, ApiType.ORDERS, ApiStatus.ERROR, 0, fromTime, e.message)
            emptyList()
        }
    }

    fun sales(startDateTime: LocalDateTime, fromTime: LocalDateTime): List<Sale> {
        return try {
            val salesResult = wbStatistics.getSales(fromTime, 0)
            saleService.save(salesResult)
            apiRequestResultService.save(startDateTime, ApiType.SALES, ApiStatus.SUCCESS, salesResult.size, fromTime)
            salesResult
        } catch (e: Exception) {
            log.error { "${e.message}: $e" }
            apiRequestResultService.save(startDateTime, ApiType.SALES, ApiStatus.ERROR, 0, fromTime, e.message)
            emptyList()
        }
    }


    fun stocks(startDateTime: LocalDateTime, fromTime: LocalDateTime): List<Stock> {
        return try {
            val stocksResult = wbStatistics.getStocks(fromTime)
            stockService.save(stocksResult)
            apiRequestResultService.save(startDateTime, ApiType.STOCKS, ApiStatus.SUCCESS, stocksResult.size, fromTime)
            stocksResult
        } catch (e: Exception) {
            log.error { "${e.message}: $e" }
            apiRequestResultService.save(startDateTime, ApiType.STOCKS, ApiStatus.ERROR, 0, fromTime, e.message)
            emptyList()
        }
    }


    fun incomes(startDateTime: LocalDateTime, fromTime: LocalDateTime): List<Income> {
        return try {
            val incomesResult = wbStatistics.getIncomes(fromTime)
            incomeService.save(incomesResult)
            apiRequestResultService.save(
                startDateTime,
                ApiType.INCOMES,
                ApiStatus.SUCCESS,
                incomesResult.size,
                fromTime
            )
            incomesResult
        } catch (e: Exception) {
            log.error { "${e.message}: $e" }
            apiRequestResultService.save(startDateTime, ApiType.STOCKS, ApiStatus.ERROR, 0, fromTime, e.message)
            emptyList()
        }
    }

    companion object {
        val log = KotlinLogging.logger {}
    }
}