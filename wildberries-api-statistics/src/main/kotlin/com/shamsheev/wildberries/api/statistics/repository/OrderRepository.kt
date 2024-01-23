package com.shamsheev.wildberries.api.statistics.repository

import com.shamsheev.wildberries.api.statistics.model.Order
import com.shamsheev.wildberries.api.statistics.model.Product
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OrderRepository : CrudRepository<Order, String> {

    fun getOrderBySrId(id: String): Optional<Order>

    fun getOrdersByProduct(product: Product): List<Order>
}