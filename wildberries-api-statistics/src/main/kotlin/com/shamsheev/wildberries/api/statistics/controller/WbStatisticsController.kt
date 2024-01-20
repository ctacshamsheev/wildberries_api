package com.shamsheev.wildberries.api.statistics.controller

import com.shamsheev.wildberries.api.statistics.ports.WbStatistics
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@Controller
@RequestMapping("/wildberries/statistics")
class WbStatisticsController(
    val wbStatistics: WbStatistics,
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
        model.addAttribute("orders", results)
        return "order"
    }

    @GetMapping("/sales")
    fun sales(@RequestBody dateTime: LocalDateTime?, int: Int?, model: Model): String {

        val dateFrom = dateTime ?: LocalDateTime.now().minusDays(5)
        val flag = int ?: 1
        val results = wbStatistics.getSales(dateFrom, flag)
        model.addAttribute("sales", results)
        return "sale"
    }
}