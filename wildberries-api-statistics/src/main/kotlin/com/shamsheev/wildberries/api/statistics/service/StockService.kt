package com.shamsheev.wildberries.api.statistics.service

import com.shamsheev.wildberries.api.statistics.model.Stock
import com.shamsheev.wildberries.api.statistics.repository.StockRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class StockService(
    @Autowired
    val stockRepository: StockRepository,
    @Autowired
    val productService: ProductService,
) {
    @Transactional
    fun save(stockList: List<Stock>) {
        stockRepository.deleteAll()
        stockList.forEach { stock ->
            productService.saveIfNotExist(stock.product)
            stockRepository.save(stock)
        }
    }
}