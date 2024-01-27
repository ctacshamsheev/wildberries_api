package com.shamsheev.wildberries.api.statistics.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "api_request_result_table")
data class ApiRequestResult(
    @Id
    @GeneratedValue
    var id: Long? = null,

    @Column(name = "start_date_time")
    val startDateTime: LocalDateTime,

    @Column(name = "end_date_time")
    val endDateTime: LocalDateTime,

    @Column(name = "api_type")
    @Enumerated(EnumType.STRING)
    val apiType: ApiType,

    @Column(name = "api_status")
    @Enumerated(EnumType.STRING)
    val apiStatus: ApiStatus,

    @Column(name = "error_message")
    val errorMessage: String? = null,

    @Column(name = "count")
    val count: Int,
)
