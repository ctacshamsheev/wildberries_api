package com.shamsheev.wildberries.api.statistics.service

import com.shamsheev.wildberries.api.statistics.model.Sale
import com.shamsheev.wildberries.api.statistics.repository.SaleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class SaleService(
    @Autowired
    val saleRepository: SaleRepository,
    @Autowired
    val productService: ProductService,
) {
    @Transactional
    fun save(sale: Sale) {
        productService.saveIfNotExist(sale.product)
        if (!saleRepository.existsBySrId(sale.srId))
            saleRepository.save(sale)
    }

    fun save(salesList: List<Sale>) {
        salesList.forEach(::save)
    }
}