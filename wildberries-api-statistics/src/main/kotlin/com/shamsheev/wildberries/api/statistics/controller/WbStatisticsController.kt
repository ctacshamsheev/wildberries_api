package com.shamsheev.wildberries.api.statistics.controller

import com.shamsheev.wildberries.api.statistics.ports.WbStatistics
import com.shamsheev.wildberries.api.statistics.service.OrderService
import com.shamsheev.wildberries.api.statistics.service.SaleService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@Controller
@RequestMapping("/wildberries/statistics")
class WbStatisticsController(
    val wbStatistics: WbStatistics,
    val orderService: OrderService,
    val saleService: SaleService,
) {

    @GetMapping("/incomes")
    fun incomes(@RequestBody dateTime: LocalDateTime?, model: Model): String {
        val dateFrom = dateTime ?: LocalDateTime.now().minusDays(5)
        val results = wbStatistics.getIncomes(dateFrom)
        model.addAttribute("incomes", results)
        return "income"
    }

    @GetMapping("/stocks")
    fun stocks(@RequestBody dateTime: LocalDateTime?, model: Model): String {
        val dateFrom = dateTime ?: LocalDateTime.now().minusDays(5)
        val results = wbStatistics.getStocks(dateFrom)
        model.addAttribute("stocks", results)
        return "stock"
    }

    @GetMapping("/orders")
    fun orders(@RequestBody dateTime: LocalDateTime?, int: Int?, model: Model): String {
        val dateFrom = dateTime ?: LocalDateTime.now().minusDays(5)
        val flag = int ?: 1
        val results = wbStatistics.getOrders(dateFrom, flag)
        results.forEach { order ->
            orderService.save(order)
        }
        model.addAttribute("orders", results)
        return "order"
    }

    @GetMapping("/sales")
    fun sales(@RequestBody dateTime: LocalDateTime?, int: Int?, model: Model): String {
        val dateFrom = dateTime ?: LocalDateTime.now().minusDays(5)
        val flag = int ?: 1
        val results = wbStatistics.getSales(dateFrom, flag)
        results.forEach { sale ->
            saleService.save(sale)
        }
        model.addAttribute("sales", results)
        return "sale"
    }
//    companion object {
//        val log = KotlinLogging.logger {}
//    }
}