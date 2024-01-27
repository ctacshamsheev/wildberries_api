package com.shamsheev.wildberries.api.statistics.adapter

import com.shamsheev.wildberries.api.statistics.model.ApiType
import com.shamsheev.wildberries.api.statistics.ports.event.ApiSchedulingEvent
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationEventPublisher
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class SchedulingAdapter(
    val publisher: ApplicationEventPublisher,
) {
    @Value("\${wildberries.statistics.orders.cron.enabled:false}")
    var isEnabledOrders: Boolean = false

    @Value("\${wildberries.statistics.sales.cron.enabled:false}")
    var isEnabledSales: Boolean = false

    @Value("\${wildberries.statistics.report.cron.enabled:false}")
    var isEnabledReport: Boolean = false

    @Value("\${wildberries.statistics.incomes.cron.enabled:false}")
    var isEnabledIncomes: Boolean = false

    @Value("\${wildberries.statistics.stocks.cron.enabled:false}")
    var isEnabledStocks: Boolean = false


    @Scheduled(cron = "\${wildberries.statistics.orders.cron.value}")
    fun ordersScheduling(
    ) {
        publishEvent(isEnabledOrders, ApiType.ORDERS)
    }

    @Scheduled(cron = "\${wildberries.statistics.sales.cron.value}")
    fun salesScheduling(
    ) {
        publishEvent(isEnabledSales, ApiType.SALES)
    }

    @Scheduled(cron = "\${wildberries.statistics.report.cron.value}")
    fun reportScheduling(
    ) {
        publishEvent(isEnabledReport, ApiType.REPORT)
    }

    @Scheduled(cron = "\${wildberries.statistics.incomes.cron.value}")
    fun incomesScheduling(
    ) {
        publishEvent(isEnabledIncomes, ApiType.INCOMES)
    }

    @Scheduled(cron = "\${wildberries.statistics.stocks.cron.value}")
    fun stocks(
    ) {
        publishEvent(isEnabledStocks, ApiType.STOCKS)
    }

    private fun publishEvent(isEnabled: Boolean, type: ApiType) {
        if (isEnabled) {
            publisher.publishEvent(
                ApiSchedulingEvent(
                    type = type
                )
            )
        }
    }
}