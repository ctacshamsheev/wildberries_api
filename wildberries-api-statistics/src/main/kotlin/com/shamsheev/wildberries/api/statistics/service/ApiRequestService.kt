package com.shamsheev.wildberries.api.statistics.service

import com.shamsheev.wildberries.api.statistics.model.ApiRequestResult
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
            apiRequestResultService.save(
                ApiRequestResult(
                    startDateTime = startDateTime,
                    endDateTime = LocalDateTime.now(),
                    apiType = ApiType.ORDERS,
                    apiStatus = ApiStatus.SUCCESS,
                    count = ordersResult.size
                )
            )
        } catch (e: Exception) {
            log.error { "${e.message}: $e" }
            apiRequestResultService.save(
                ApiRequestResult(
                    startDateTime = startDateTime,
                    endDateTime = LocalDateTime.now(),
                    apiType = ApiType.ORDERS,
                    apiStatus = ApiStatus.ERROR,
                    errorMessage = e.message,
                    count = 0
                )
            )
        }
    }

    fun sales(startDateTime: LocalDateTime) {
        try {
            val fromTime = apiRequestResultService.getLastSuccessDateByApiType(ApiType.SALES)
            val salesResult = wbStatistics.getSales(fromTime.get().minusHours(timezone), 0)
            saleService.save(salesResult)
            apiRequestResultService.save(
                ApiRequestResult(
                    startDateTime = startDateTime,
                    endDateTime = LocalDateTime.now(),
                    apiType = ApiType.SALES,
                    apiStatus = ApiStatus.SUCCESS,
                    count = salesResult.size
                )
            )
        } catch (e: Exception) {
            log.error { "${e.message}: $e" }
            apiRequestResultService.save(
                ApiRequestResult(
                    startDateTime = startDateTime,
                    endDateTime = LocalDateTime.now(),
                    apiType = ApiType.SALES,
                    apiStatus = ApiStatus.ERROR,
                    errorMessage = e.message,
                    count = 0
                )
            )
        }
    }

    companion object {
        val log = KotlinLogging.logger {}
    }
}