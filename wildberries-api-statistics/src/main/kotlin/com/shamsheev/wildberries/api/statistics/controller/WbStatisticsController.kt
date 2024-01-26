package com.shamsheev.wildberries.api.statistics.controller

import com.shamsheev.wildberries.api.statistics.ports.WbStatistics
import com.shamsheev.wildberries.api.statistics.service.OrderService
import com.shamsheev.wildberries.api.statistics.service.SaleService
import com.shamsheev.wildberries.api.statistics.service.StockService
import mu.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest


@Controller
@RequestMapping("/wildberries/statistics")
class WbStatisticsController(
    val wbStatistics: WbStatistics,
    val orderService: OrderService,
    val saleService: SaleService,
    val stockService: StockService,
) {

    @GetMapping("/incomes")
    fun incomes(@RequestBody dateTime: LocalDateTime?, model: Model): String {
        val dateFrom = dateTime ?: LocalDateTime.now().minusDays(30)
        val results = wbStatistics.getIncomes(dateFrom)
        model.addAttribute("incomes", results)
        return "income"
    }

    @GetMapping("/stocks")
    fun stocks(@RequestBody dateTime: LocalDateTime?, model: Model): String {
        val dateFrom = dateTime ?: LocalDateTime.now().minusYears(3)
        val results = wbStatistics.getStocks(dateFrom)
        stockService.save(results)
        model.addAttribute("stocks", results)
        return "stock"
    }

    @GetMapping("/orders")
    fun orders(@RequestBody dateTime: LocalDateTime?, int: Int?, model: Model): String {
        val dateFrom = dateTime ?: LocalDateTime.now().minusDays(5)
        val flag = int ?: 1
        val results = wbStatistics.getOrders(dateFrom, flag)
        orderService.save(results)
        model.addAttribute("orders", results)
        return "order"
    }

    @GetMapping("/sales")
    fun sales(@RequestBody dateTime: LocalDateTime?, int: Int?, model: Model): String {
        val dateFrom = dateTime ?: LocalDateTime.now().minusDays(5)
        val flag = int ?: 1
        val results = wbStatistics.getSales(dateFrom, flag)
        saleService.save(results)
        model.addAttribute("sales", results)
        return "sale"
    }

    @ExceptionHandler(Exception::class)
    fun handleError(req: HttpServletRequest, ex: Exception): ModelAndView? {
        log.error("Request: " + req.requestURL + " raised " + ex)
        val modelAndView = ModelAndView()
        modelAndView.addObject("exception", ex.message)
        modelAndView.viewName = "error"
        return modelAndView
    }

    companion object {
        val log = KotlinLogging.logger {}
    }
}
