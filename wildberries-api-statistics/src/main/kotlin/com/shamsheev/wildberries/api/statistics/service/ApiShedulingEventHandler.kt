package com.shamsheev.wildberries.api.statistics.service

import com.shamsheev.wildberries.api.statistics.model.ApiType
import com.shamsheev.wildberries.api.statistics.port.event.ApiSchedulingEvent
import mu.KotlinLogging
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ApiShedulingEventHandler(
    val apiRequestService: ApiRequestService,
    val apiRequestResultService: ApiRequestResultService,
) {

    @EventListener(ApiSchedulingEvent::class)
    fun ordersScheduling(event: ApiSchedulingEvent) {
        val now = LocalDateTime.now()
        val start: LocalDateTime
        when (event.type) {
            ApiType.ORDERS -> {
                start = getStartDate(ApiType.ORDERS)
                log.info { "ordersScheduling: $now, $start" }
                apiRequestService.orders(now, start)
            }
            ApiType.SALES -> {
                start = getStartDate(ApiType.SALES)
                log.info { "salesScheduling: $now, $start" }
                apiRequestService.sales(now, start)
            }
            ApiType.STOCKS -> {
                start = getStartDate(ApiType.STOCKS)
                log.info { "stocksScheduling: $now, $start" }
                apiRequestService.stocks(now, start)
            }
            ApiType.INCOMES -> {
                start = getStartDate(ApiType.INCOMES)
                log.info { "incomesScheduling: $now, $start" }
                apiRequestService.incomes(now, start)
            }
            ApiType.REPORT -> {
                start = getStartDate(ApiType.REPORT)
                log.info { "reportScheduling: $now, $start" }
            }
        }
    }

    private fun getStartDate(apiType: ApiType): LocalDateTime {
        return apiRequestResultService.getLastSuccessDateByApiType(apiType)
            .orElse(LocalDateTime.MIN)
    }

    companion object {
        val log = KotlinLogging.logger {}
    }
}