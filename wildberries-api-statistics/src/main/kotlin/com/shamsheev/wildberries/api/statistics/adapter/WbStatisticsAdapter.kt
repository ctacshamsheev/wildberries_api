package com.shamsheev.wildberries.api.statistics.adapter

import com.shamsheev.wildberries.api.statistics.GetApiException
import com.shamsheev.wildberries.api.statistics.model.Order
import com.shamsheev.wildberries.api.statistics.model.Product
import com.shamsheev.wildberries.api.statistics.ports.WbStatistics
import mu.KotlinLogging
import openapi.wildberries.ru.statistics.apis.DefaultApi
import openapi.wildberries.ru.statistics.models.*
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime

private const val GET_INCOMES = "GET https://statistics-api.wildberries.ru/api/v1/supplier/incomes"
private const val GET_STOCKS = "GET https://statistics-api.wildberries.ru/api/v1/supplier/stocks"
private const val GET_ORDERS = "GET https://statistics-api.wildberries.ru/api/v1/supplier/orders"
private const val GET_SALES = "GET https://statistics-api.wildberries.ru/api/v1/supplier/sales"
private const val GET_REPORT = "GET https://statistics-api.wildberries.ru/api/v1/supplier/reportDetailByPeriod"

@Service
class WbStatisticsAdapter(
    @Qualifier("statisticsApi")
    val api: DefaultApi,
) : WbStatistics {

    override fun getIncomes(
        dateFrom: LocalDateTime,
    ): List<IncomesItem> {
        log.info { "$GET_INCOMES?dateFrom=${dateFrom}" }
        try {
            val results = api.apiV1SupplierIncomesGet(dateFrom.toString())
            log.info { "$GET_INCOMES?dateFrom=${dateFrom} return: ${results.size} records" }
            return results
        } catch (ex: Exception) {
            log.error { "Error $GET_INCOMES?dateFrom=${dateFrom}: ${ex.message} ${ex.stackTrace}" }
            throw GetApiException("Error $GET_INCOMES?dateFrom=${dateFrom}: ${ex.message}")
        }
    }

    override fun getStocks(
        dateFrom: LocalDateTime,
    ): List<StocksItem> {
        log.info { "$GET_STOCKS?dateFrom=${dateFrom}" }
        try {
            val results = api.apiV1SupplierStocksGet(dateFrom.toString())
            log.info { "$GET_STOCKS?dateFrom=${dateFrom} return: ${results.size} records" }
            return results
        } catch (ex: Exception) {
            log.error { "Error $GET_STOCKS?dateFrom=${dateFrom}: ${ex.message} ${ex.stackTrace}" }
            throw GetApiException("Error $GET_STOCKS?dateFrom=${dateFrom}: ${ex.message}")
        }
    }

    override fun getOrders(
        dateFrom: LocalDateTime,
        flag: Int,
    ): List<Order> {
        log.info { "$GET_ORDERS?dateFrom=${dateFrom}&flag=${flag}" }
        try {
            val results = api.apiV1SupplierOrdersGet(dateFrom.toString(), flag)
            log.info { "$GET_ORDERS?dateFrom=${dateFrom}&flag=${flag} return: ${results.size} records" }

            results.forEach { w -> log.info { w } }
            return results.map { ordersItem -> ordersItem.toOrder() }
                .toList()

        } catch (ex: Exception) {
            log.error { "Error $GET_ORDERS?dateFrom=${dateFrom}&flag=${flag}: ${ex.message} ${ex.stackTrace}" }
            throw GetApiException("Error $GET_ORDERS?dateFrom=${dateFrom}&flag=${flag}: ${ex.message}")
        }
    }

    override fun getSales(
        dateFrom: LocalDateTime,
        flag: Int,
    ): List<SalesItem> {
        log.info { "$GET_SALES?dateFrom=${dateFrom}&flag=${flag}" }
        try {
            val results = api.apiV1SupplierSalesGet(dateFrom.toString(), flag)
            log.info { "$GET_SALES?dateFrom=${dateFrom}&flag=${flag} return: ${results.size} records" }
            return results
        } catch (ex: Exception) {
            log.error { "Error $GET_SALES?dateFrom=${dateFrom}&flag=${flag}: ${ex.message} ${ex.stackTrace}" }
            throw GetApiException("Error $GET_SALES?dateFrom=${dateFrom}&flag=${flag}: ${ex.message}")
        }
    }

    override fun getReport(
        dateFrom: LocalDate,
        dateTo: LocalDate,
    ): List<DetailReportItem> {
        log.info { "$GET_REPORT?dateFrom=${dateFrom}&dateTo=${dateTo}" }
        try {
            val results = api.apiV1SupplierReportDetailByPeriodGet(dateFrom.toString(), dateTo)
            log.info { "$GET_REPORT?dateFrom=${dateFrom}&dateTo=${dateTo} return: ${results.size} records" }
            return results
        } catch (ex: Exception) {
            log.error { "Error $GET_REPORT?dateFrom=${dateFrom}&dateTo=${dateTo}: ${ex.message} ${ex.stackTrace}" }
            throw GetApiException("Error $GET_REPORT?dateFrom=${dateFrom}&dateTo=${dateTo}: ${ex.message}")
        }
    }

    companion object {
        val log = KotlinLogging.logger {}
    }


    fun OrdersItem.toOrder() = Order(
        srId = srid,
        date = date,
        lastChangeDate = lastChangeDate,
        warehouseName = warehouseName,
        countryName = countryName,
        oblastName = oblastOkrugName,
        regionName = regionName,
        product = Product(
            id = barcode!!,
            supId = supplierArticle!!,
            wbId = nmId!!.toLong(),
            category = category,
            subject = subject,
            brand = brand,
            size = techSize,

            ),
        incomeId = incomeID,
        isSupply = isSupply,
        isRealization = isRealization,
        totalPrice = totalPrice,
        discountPercent = discountPercent,
        salePrice = spp,
        finishPrice = finishedPrice,
        priceWithDiscount = priceWithDisc,
        isCancel = isCancel,
        cancelDate = cancelDate,
        orderType = orderType,
        sticker = sticker,
        gNumber = gNumber,
    )

}