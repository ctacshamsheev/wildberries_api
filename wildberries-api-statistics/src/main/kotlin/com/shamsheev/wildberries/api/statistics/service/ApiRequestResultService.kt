package com.shamsheev.wildberries.api.statistics.service

import com.shamsheev.wildberries.api.statistics.model.ApiRequestResult
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

    fun isFirstStart(apiType: ApiType): Boolean {
        return !apiRequestResultRepository.existsByApiType(apiType)
    }
}