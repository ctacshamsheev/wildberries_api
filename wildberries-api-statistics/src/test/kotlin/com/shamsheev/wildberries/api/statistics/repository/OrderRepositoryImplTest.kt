package com.shamsheev.wildberries.api.statistics.repository

import com.shamsheev.wildberries.api.statistics.model.Order
import com.shamsheev.wildberries.api.statistics.model.Product
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.time.LocalDateTime
import kotlin.test.assertEquals


@DataJpaTest
@ActiveProfiles("test")
internal class OrderRepositoryImplTest {

    @Autowired
    private lateinit var repository: OrderRepository

    @Autowired
    private lateinit var productRepository: ProductRepository

    @Test
    fun saveAndGetById() {
        //given
        val product = Product("2106904170120128", "11619197", 11619197, "Одежда", "Блузки", "Luxury Plus", "60")
        val order = Order(
            srId = "srid",
            date = LocalDateTime.now(),
            lastChangeDate = LocalDateTime.now(),
            warehouseName = "warehouseName",
            countryName = "countryName",
            oblastName = "oblastOkrugName",
            regionName = "regionName",
            product = product,
            incomeId = 123,
            isSupply = true,
            isRealization = true,
            totalPrice = BigDecimal.ONE,
            discountPercent = 1,
            salePrice = BigDecimal.ONE,
            finishPrice = BigDecimal.TEN,
            priceWithDiscount = BigDecimal.ONE,
            isCancel = false,
            cancelDate = null,
            orderType = "orderType",
            sticker = "sticker",
            gNumber = "gNumber",
        )
        //when
        val productResult = productRepository.save(product)
        val result = repository.save(order)
        val find = repository.findAllByDateBetweenOrderByDate(
            LocalDateTime.now().minusHours(1),
            LocalDateTime.now()
        )
        val empty = repository.findAllByDateBetweenOrderByDate(
            LocalDateTime.now().minusHours(2),
            LocalDateTime.now().minusHours(1)
        )
        //then
        assertEquals(product, productResult)
        assertEquals(order, result)
        assertEquals(find[0], result)
        assertEquals(find.size, 1)
        assertEquals(empty.size, 0)

    }
}