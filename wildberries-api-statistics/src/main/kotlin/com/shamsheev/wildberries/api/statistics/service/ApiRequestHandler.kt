package com.shamsheev.wildberries.api.statistics.service

import com.shamsheev.wildberries.api.statistics.model.ApiType
import com.shamsheev.wildberries.api.statistics.ports.event.ApiSchedulingEvent
import mu.KotlinLogging
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ApiRequestHandler(
    val apiRequestService: ApiRequestService,
) {

    @EventListener(ApiSchedulingEvent::class)
    fun ordersScheduling(event: ApiSchedulingEvent) {
        val now = LocalDateTime.now();
        when (event.type) {
            ApiType.ORDERS -> {
                log.info { "ordersScheduling: $now" }
                apiRequestService.orders(now)
            }
            ApiType.SALES -> {
                log.info { "salesScheduling: $now" }
                apiRequestService.sales(now)
            }
            ApiType.STOCKS -> {
                log.info { "stocksScheduling: $now" }
            }
            ApiType.INCOMES -> {
                log.info { "incomesScheduling: $now" }
            }
            ApiType.REPORT -> {
                log.info { "reportScheduling: $now" }
            }
        }
    }

    companion object {
        val log = KotlinLogging.logger {}
    }
}