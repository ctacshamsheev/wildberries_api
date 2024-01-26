package com.shamsheev.wildberries.api.statistics.repository

import com.shamsheev.wildberries.api.statistics.model.Sale
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SaleRepository : CrudRepository<Sale, String> {

    fun existsBySrId(id: String?): Boolean
}