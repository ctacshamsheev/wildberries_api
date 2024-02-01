package com.shamsheev.wildberries.api.statistics.repository

import com.shamsheev.wildberries.api.statistics.model.ApiRequestResult
import com.shamsheev.wildberries.api.statistics.model.ApiStatus
import com.shamsheev.wildberries.api.statistics.model.ApiType
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface ApiRequestResultRepository : CrudRepository<ApiRequestResult, Long> {

    fun findAllByApiTypeAndApiStatus(apiType: ApiType, apiStatus: ApiStatus = ApiStatus.SUCCESS): List<ApiRequestResult>

    fun existsByApiType(apiType: ApiType): Boolean

    fun findAllByStartDateTimeBetweenOrderByEndDateTimeDesc(
        start: LocalDateTime,
        and: LocalDateTime,
    ): List<ApiRequestResult>
}