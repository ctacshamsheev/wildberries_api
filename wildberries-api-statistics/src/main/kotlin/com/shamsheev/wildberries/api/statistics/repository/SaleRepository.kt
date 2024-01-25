package com.shamsheev.wildberries.api.statistics.repository

import com.shamsheev.wildberries.api.statistics.model.Product
import com.shamsheev.wildberries.api.statistics.model.Sale
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SaleRepository : CrudRepository<Sale, String> {

    fun existsBySrId(id: String?): Boolean

    fun getOrderBySrId(id: String): Optional<Sale>

    fun getSalesByProduct(product: Product): List<Sale>
}