package com.shamsheev.wildberries.api.statistics.repository

import com.shamsheev.wildberries.api.statistics.model.Sale
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface SaleRepository : CrudRepository<Sale, String> {

    fun existsBySrId(id: String?): Boolean
    fun findAllByDateBetweenOrderByDate(startTime: LocalDateTime, endTime: LocalDateTime): List<Sale>
}