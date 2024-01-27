package com.shamsheev.wildberries.api.statistics.config

import com.shamsheev.wildberries.api.statistics.model.ApiRequestResult
import com.shamsheev.wildberries.api.statistics.model.ApiStatus
import com.shamsheev.wildberries.api.statistics.model.ApiType
import com.shamsheev.wildberries.api.statistics.service.ApiRequestResultService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.EnableScheduling
import java.time.LocalDateTime


@Configuration
@EnableScheduling
class SchedulingConfig(
    @Autowired val apiRequestResultService: ApiRequestResultService,
) {
    @Value("\${wildberries.statistics.orders.startDate:2024-01-01T00:00:00.0}")
    private var ordersStartDate: String = ""

    @Value("\${wildberries.statistics.sales.startDate:2024-01-01T00:00:00.0}")
    private var salesStartDate: String = ""

    @Value("\${wildberries.statistics.report.startDate:2024-01-01T00:00:00.0}")
    private val reportStartDate: String = ""

    @Value("\${wildberries.statistics.incomes.startDate:2024-01-01T00:00:00.0}")
    private var incomesStartDate: String = ""

    @Value("\${wildberries.statistics.stocks.startDate:2020-01-01T00:00:00.0}")
    private val stocksStartDate: String = ""

    @EventListener(ApplicationReadyEvent::class)
    fun firstInitAfterStartup() {
        initFirstDate(ApiType.ORDERS, ordersStartDate)
        initFirstDate(ApiType.SALES, salesStartDate)
        initFirstDate(ApiType.INCOMES, incomesStartDate)
        initFirstDate(ApiType.STOCKS, stocksStartDate)
        initFirstDate(ApiType.REPORT, reportStartDate)
    }

    private fun initFirstDate(
        apiType: ApiType,
        startStr: String,
    ) {
        if (apiRequestResultService.isFirstStart(apiType)) {
            val startTime = LocalDateTime.parse(startStr)
            apiRequestResultService.save(
                ApiRequestResult(
                    startDateTime = startTime,
                    endDateTime = startTime,
                    apiType = apiType,
                    apiStatus = ApiStatus.SUCCESS,
                    errorMessage = "first init",
                    count = 0
                )
            )
        }
    }
}