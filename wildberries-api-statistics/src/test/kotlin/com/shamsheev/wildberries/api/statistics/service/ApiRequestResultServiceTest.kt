package com.shamsheev.wildberries.api.statistics.service

import com.shamsheev.wildberries.api.statistics.model.ApiRequestResult
import com.shamsheev.wildberries.api.statistics.model.ApiStatus
import com.shamsheev.wildberries.api.statistics.model.ApiType
import com.shamsheev.wildberries.api.statistics.repository.ApiRequestResultRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDateTime
import java.util.*
import kotlin.test.assertEquals


@DataJpaTest
@ActiveProfiles("test")
internal class ApiRequestResultServiceTest {

    @Autowired
    private lateinit var repository: ApiRequestResultRepository

    @Test
    fun getIfEmpty() {
        //given
        val apiRequestResultService = ApiRequestResultService(repository)
        //when
        val result = apiRequestResultService.getLastSuccessDateByApiType(ApiType.ORDERS)
        val isEmpty = apiRequestResultService.isFirstStart(ApiType.ORDERS)
        //then
        assertEquals(result, Optional.empty())
        assertEquals(isEmpty, true)
    }

    @Test
    fun getMax() {
        //given
        val apiRequestResultService = ApiRequestResultService(repository)
        val time = LocalDateTime.now()
        apiRequestResultService.save(
            ApiRequestResult(
                start = time.plusDays(2),
                end = time.plusDays(3),
                apiType = ApiType.ORDERS,
                apiStatus = ApiStatus.SUCCESS,
                count = 0,
                from = LocalDateTime.now()
            )
        )
        apiRequestResultService.save(
            ApiRequestResult(
                start = time.minusDays(2),
                end = time.minusDays(2),
                apiType = ApiType.ORDERS,
                apiStatus = ApiStatus.SUCCESS,
                count = 0,
                from = LocalDateTime.now()
            )
        )
        val isEmptyOrders = apiRequestResultService.isFirstStart(ApiType.ORDERS)
        val isEmptySales = apiRequestResultService.isFirstStart(ApiType.SALES)
        //when
        val result = apiRequestResultService.getLastSuccessDateByApiType(ApiType.ORDERS)
        //then
        assertEquals(result.get(), time.plusDays(2))
        assertEquals(isEmptyOrders, false)
        assertEquals(isEmptySales, true)
    }

    @Test
    fun getMaxWithStatusAndType() {
        //given
        val apiRequestResultService = ApiRequestResultService(repository)
        val time = LocalDateTime.now()
        apiRequestResultService.save(
            ApiRequestResult(
                start = time.minusDays(1),
                end = time.minusDays(2),
                apiType = ApiType.ORDERS,
                apiStatus = ApiStatus.SUCCESS,
                count = 0,
                from = LocalDateTime.now()
            )
        )
        apiRequestResultService.save(
            ApiRequestResult(
                start = time,
                end = time.plusDays(1),
                apiType = ApiType.ORDERS,
                apiStatus = ApiStatus.SUCCESS,
                count = 0,
                from = LocalDateTime.now()
            )
        )
        apiRequestResultService.save(
            ApiRequestResult(
                start = time.plusDays(1),
                end = time.plusDays(2),
                apiType = ApiType.SALES,
                apiStatus = ApiStatus.SUCCESS,
                count = 0,
                from = LocalDateTime.now()
            )
        )

        apiRequestResultService.save(
            ApiRequestResult(
                start = time.plusDays(2),
                end = time.plusDays(3),
                apiType = ApiType.ORDERS,
                apiStatus = ApiStatus.ERROR,
                count = 0,
                from = LocalDateTime.now()
            )
        )

        //when
        val result = apiRequestResultService.getLastSuccessDateByApiType(ApiType.ORDERS)
        //then
        assertEquals(result.get(), time)
    }
}