package com.shamsheev.wildberries.api.statistics.repository

import com.shamsheev.wildberries.api.statistics.model.Product
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import kotlin.test.assertEquals


@DataJpaTest
@ActiveProfiles("test")
internal class ProductRepositoryImplTest {

    @Autowired
    private lateinit var repository: ProductRepository

    @Test
    fun saveAndGetById() {
        //given
        val product = Product("2106904170120128", "11619197", 11619197, "Одежда", "Блузки", "Luxury Plus", "60")

        repository.save(product)
        //when
        val result = repository.getProductById(product.id).get()
        //then
        assertEquals(product, result)
    }
}