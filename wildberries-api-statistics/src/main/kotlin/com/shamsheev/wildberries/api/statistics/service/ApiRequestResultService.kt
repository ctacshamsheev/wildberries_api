package com.shamsheev.wildberries.api.statistics.service

import com.shamsheev.wildberries.api.statistics.model.ApiRequestResult
import com.shamsheev.wildberries.api.statistics.model.ApiStatus
import com.shamsheev.wildberries.api.statistics.model.ApiType
import com.shamsheev.wildberries.api.statistics.repository.ApiRequestResultRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class ApiRequestResultService(
    @Autowired
    val apiRequestResultRepository: ApiRequestResultRepository,
) {
    fun save(apiRequestResult: ApiRequestResult) {
        apiRequestResultRepository.save(apiRequestResult)
    }

    fun getLastSuccessDateByApiType(apiType: ApiType): Optional<LocalDateTime> {
        return Optional.ofNullable(apiRequestResultRepository.findAllByApiTypeAndApiStatus(apiType)
            .map { it.startDateTime }
            .maxByOrNull { it })
    }

    fun isFirstStart(apiType: ApiType) = !apiRequestResultRepository.existsByApiType(apiType)

    fun findAllByDateBetween(start: LocalDateTime, and: LocalDateTime) =
        apiRequestResultRepository.findAllByStartDateTimeBetweenOrderByEndDateTimeDesc(start, and)

    fun save(
        startDateTime: LocalDateTime,
        apiType: ApiType,
        apiStatus: ApiStatus,
        count: Int,
        errorMessage: String? = null,
    ) {
        save(
            ApiRequestResult(
                startDateTime = startDateTime,
                endDateTime = LocalDateTime.now(),
                apiType = apiType,
                apiStatus = apiStatus,
                errorMessage = errorMessage,
                count = count

            )
        )
    }

}