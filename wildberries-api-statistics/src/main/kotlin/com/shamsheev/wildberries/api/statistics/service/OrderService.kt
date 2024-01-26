package com.shamsheev.wildberries.api.statistics.service

import com.shamsheev.wildberries.api.statistics.model.Order
import com.shamsheev.wildberries.api.statistics.repository.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class OrderService(
    @Autowired
    val orderRepository: OrderRepository,
    @Autowired
    val productService: ProductService,
) {
    @Transactional
    fun save(order: Order) {
        order.product = productService.saveIfNotExist(order.product)
        if (!orderRepository.existsBySrId(order.srId))
            orderRepository.save(order)
    }

    fun save(ordersList: List<Order>) {
        ordersList.forEach(::save)
    }
}