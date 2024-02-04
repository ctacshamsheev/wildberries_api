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
            .map { it.start }
            .maxByOrNull { it })
    }

    fun isFirstStart(apiType: ApiType) = !apiRequestResultRepository.existsByApiType(apiType)

    fun findAllByDateBetween(start: LocalDateTime, and: LocalDateTime) =
        apiRequestResultRepository.findAllByStartBetweenOrderByEndDesc(start, and)

    fun save(
        startDateTime: LocalDateTime,
        apiType: ApiType,
        apiStatus: ApiStatus,
        count: Int,
        fromDateTime: LocalDateTime,
        errorMessage: String? = null,
    ) {
        save(
            ApiRequestResult(
                start = startDateTime,
                end = LocalDateTime.now(),
                apiType = apiType,
                apiStatus = apiStatus,
                errorMessage = if (errorMessage != null && errorMessage.length > 254) {
                    errorMessage.substring(0, 254)
                } else {
                    errorMessage
                },
                count = count,
                from = fromDateTime
            )
        )
    }

}