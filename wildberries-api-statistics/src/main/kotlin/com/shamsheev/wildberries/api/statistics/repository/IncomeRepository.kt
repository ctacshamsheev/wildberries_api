package com.shamsheev.wildberries.api.statistics.repository

import com.shamsheev.wildberries.api.statistics.model.Income
import com.shamsheev.wildberries.api.statistics.model.Product
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface IncomeRepository : CrudRepository<Income, Long> {

    fun existsByIncomeIdAndDateAndProduct(incomeId: Int?, date: LocalDate?, product: Product): Boolean
}