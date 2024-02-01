package com.shamsheev.wildberries.api.statistics.repository

import com.shamsheev.wildberries.api.statistics.model.Stock
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface StockRepository : CrudRepository<Stock, Long> {
    fun findAllByLastChangeDateBetweenOrderByWarehouseName(start: LocalDateTime, end: LocalDateTime): List<Stock>
}