package com.shamsheev.wildberries.api.statistics.ports.event

import com.shamsheev.wildberries.api.statistics.model.ApiType
import org.springframework.context.ApplicationEvent

data class ApiSchedulingEvent(
    val type: ApiType,
) : ApplicationEvent(type)
