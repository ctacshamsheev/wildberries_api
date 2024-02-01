package com.shamsheev.wildberries.api.statistics.controller

import com.shamsheev.wildberries.api.statistics.ports.WbStatistics
import com.shamsheev.wildberries.api.statistics.service.IncomeService
import com.shamsheev.wildberries.api.statistics.service.OrderService
import com.shamsheev.wildberries.api.statistics.service.SaleService
import com.shamsheev.wildberries.api.statistics.service.StockService
import mu.KotlinLogging
import org.springframework.core.io.FileSystemResource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import java.io.File
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest


@Controller
@RequestMapping("/wildberries/statistics")
class WbStatisticsController(
    val wbStatistics: WbStatistics,
    val orderService: OrderService,
    val saleService: SaleService,
    val stockService: StockService,
    val incomeService: IncomeService,
) {

    @GetMapping("/orders")
    fun orders(
        @RequestParam(value = "start") start: String,
        @RequestParam(value = "end") end: String,
        model: Model,
    ): String {
        val results = orderService.findAllByDateBetween(LocalDateTime.parse(start), LocalDateTime.parse(end))
        model.addAttribute("orders", results)
        return "order"
    }

    @GetMapping("/orders/download", produces = ["text/csv"])
    fun ordersCsv(
        @RequestParam(value = "start") start: String,
        @RequestParam(value = "end") end: String,
        model: Model,
    ): ResponseEntity<FileSystemResource> {
        val file: File = orderService.writeAllByDateBetween(LocalDateTime.parse(start), LocalDateTime.parse(end))
        return ResponseEntity.ok()
            .header("Content-Disposition", "attachment; filename=orders.xls")
            .contentLength(file.length())
            .contentType(MediaType.parseMediaType("text/csv"))
            .body<FileSystemResource>(FileSystemResource(file))
    }

    @GetMapping("/sales")
    fun sales(
        @RequestParam(value = "start") start: String,
        @RequestParam(value = "end") end: String,
        model: Model,
    ): String {
        val results = saleService.findAllByDateBetween(LocalDateTime.parse(start), LocalDateTime.parse(end))
        model.addAttribute("sales", results)
        return "sale"
    }

    @GetMapping("/sales/download", produces = ["text/csv"])
    fun salesCsv(
        @RequestParam(value = "start") start: String,
        @RequestParam(value = "end") end: String,
        model: Model,
    ): ResponseEntity<FileSystemResource> {
        val file: File = saleService.writeAllByDateBetween(LocalDateTime.parse(start), LocalDateTime.parse(end))
        return ResponseEntity.ok()
            .header("Content-Disposition", "attachment; filename=sales.xls")
            .contentLength(file.length())
            .contentType(MediaType.parseMediaType("text/csv"))
            .body<FileSystemResource>(FileSystemResource(file))
    }


    @GetMapping("/incomes")
    fun incomes(@RequestBody dateTime: LocalDateTime?, model: Model): String {
        val dateFrom = dateTime ?: LocalDateTime.now().minusDays(30)
        val results = wbStatistics.getIncomes(dateFrom)
        incomeService.save(results)
        model.addAttribute("incomes", results)
        return "income"
    }

    @GetMapping("/stocks")
    fun stocks(@RequestBody dateTime: LocalDateTime?, model: Model): String {
//        val dateFrom = dateTime ?: LocalDateTime.now().minusYears(3)
        // TODO !!
        val dateFrom = dateTime ?: LocalDateTime.now().minusMonths(3)
        val results = wbStatistics.getStocks(dateFrom)
        stockService.save(results)
        model.addAttribute("stocks", results)
        return "stock"
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
