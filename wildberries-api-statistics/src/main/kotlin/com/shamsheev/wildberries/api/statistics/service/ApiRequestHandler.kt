package com.shamsheev.wildberries.api.statistics.service

import com.shamsheev.wildberries.api.statistics.model.ApiType
import com.shamsheev.wildberries.api.statistics.ports.event.ApiSchedulingEvent
import mu.KotlinLogging
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ApiRequestHandler {

    @EventListener(ApiSchedulingEvent::class)
    fun ordersScheduling(event: ApiSchedulingEvent) {
        when (event.type) {
            ApiType.ORDERS -> {
                log.info { "ordersScheduling: ${LocalDateTime.now()}" }
            }
            ApiType.SALES -> {
                log.info { "salesScheduling: ${LocalDateTime.now()}" }
            }
            ApiType.STOCKS -> {
                log.info { "stocksScheduling: ${LocalDateTime.now()}" }
            }
            ApiType.INCOMES -> {
                log.info { "incomesScheduling: ${LocalDateTime.now()}" }
            }
            ApiType.REPORT -> {
                log.info { "reportScheduling: ${LocalDateTime.now()}" }
            }
        }
    }

    companion object {
        val log = KotlinLogging.logger {}
    }
}