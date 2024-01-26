package com.shamsheev.wildberries.api.statistics.service

import com.shamsheev.wildberries.api.statistics.model.Product
import com.shamsheev.wildberries.api.statistics.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
class ProductService(
    @Autowired
    val productRepository: ProductRepository,
) {
    @Transactional
    fun saveIfNotExist(product: Product): Product {
        val productOptional = productRepository.getProductById(product.id)
        if (!productOptional.isPresent
            || (Objects.isNull(productOptional.get().category) && (!Objects.isNull(product.category)))
        ) {
            return productRepository.save(product)
        }
        return productOptional.get()
    }

    fun saveIfNotExist(productsList: List<Product>) {
        productsList.forEach(::saveIfNotExist)
    }
}