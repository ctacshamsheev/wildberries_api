package com.shamsheev.wildberries.api.statistics.controller

import com.shamsheev.wildberries.api.statistics.service.*
import org.springframework.core.io.FileSystemResource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.io.File
import java.math.BigDecimal
import java.time.LocalDateTime


@Controller
@RequestMapping("/wildberries/statistics")
class WbStatisticsController(
    val orderService: OrderService,
    val saleService: SaleService,
    val stockService: StockService,
    val incomeService: IncomeService,
    val apiRequestService: ApiRequestService,
) {

    @GetMapping("/orders")
    fun orders(
        @RequestParam(value = "start") start: String,
        @RequestParam(value = "end") end: String,
        model: Model,
    ): String {
        val results = orderService.findAllByDateBetween(LocalDateTime.parse(start), LocalDateTime.parse(end))
        val count = results.size
        val countNot = results.count { order -> order.isCancel != true }
        val prices = results
            .filter { order -> order.isCancel != true }
            .map { order -> order.totalPrice }
            .filterNotNull()
            .toList()

        var sum = BigDecimal.ZERO
        for (price in prices) {
            sum = sum.plus(price)
        }

        model.addAttribute("orders", results)
        model.addAttribute("count", count)
        model.addAttribute("countNot", countNot)
        model.addAttribute("convers", "%.2f".format(countNot.toDouble() / count.toDouble()))
        model.addAttribute("sum", sum)
        model.addAttribute("start", LocalDateTime.parse(start))
        model.addAttribute("end", LocalDateTime.parse(end))
        return "order"
    }

    @GetMapping("/orders/download", produces = ["text/csv"])
    fun ordersCsv(
        @RequestParam(value = "start") start: String,
        @RequestParam(value = "end") end: String,
    ): ResponseEntity<FileSystemResource> {
        val file: File = orderService.writeAllByDateBetween(LocalDateTime.parse(start), LocalDateTime.parse(end))
        return ResponseEntity.ok()
            .header("Content-Disposition", "attachment; filename=orders.xls")
            .contentLength(file.length())
            .contentType(MediaType.parseMediaType("text/csv"))
            .body(FileSystemResource(file))
    }

    @GetMapping("/orders/scheduling")
    fun ordersScheduling(
        @RequestParam(value = "start") start: String,
        model: Model,
    ): String {
        val results = apiRequestService.orders(LocalDateTime.now(), LocalDateTime.parse(start))
        model.addAttribute("orders", results)
        return "order"
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
    ): ResponseEntity<FileSystemResource> {
        val file: File = saleService.writeAllByDateBetween(LocalDateTime.parse(start), LocalDateTime.parse(end))
        return ResponseEntity.ok()
            .header("Content-Disposition", "attachment; filename=sales.xls")
            .contentLength(file.length())
            .contentType(MediaType.parseMediaType("text/csv"))
            .body(FileSystemResource(file))
    }

    @GetMapping("/sales/scheduling")
    fun salesScheduling(
        @RequestParam(value = "start") start: String,
        model: Model,
    ): String {
        val results = apiRequestService.sales(LocalDateTime.now(), LocalDateTime.parse(start))
        model.addAttribute("sales", results)
        return "sale"
    }

    @GetMapping("/incomes")
    fun incomes(
        @RequestParam(value = "start") start: String,
        @RequestParam(value = "end") end: String,
        model: Model,
    ): String {
        val results = incomeService.findAllByDateBetween(LocalDateTime.parse(start), LocalDateTime.parse(end))
        model.addAttribute("incomes", results)
        return "income"
    }

    @GetMapping("/incomes/download", produces = ["text/csv"])
    fun incomesCsv(
        @RequestParam(value = "start") start: String,
        @RequestParam(value = "end") end: String,
    ): ResponseEntity<FileSystemResource> {
        val file: File = incomeService.writeAllByDateBetween(LocalDateTime.parse(start), LocalDateTime.parse(end))
        return ResponseEntity.ok()
            .header("Content-Disposition", "attachment; filename=incomes.xls")
            .contentLength(file.length())
            .contentType(MediaType.parseMediaType("text/csv"))
            .body(FileSystemResource(file))
    }

    @GetMapping("/incomes/scheduling")
    fun incomesScheduling(
        @RequestParam(value = "start") start: String,
        model: Model,
    ): String {
        val results = apiRequestService.incomes(LocalDateTime.now(), LocalDateTime.parse(start))
        model.addAttribute("incomes", results)
        return "income"
    }

    @GetMapping("/stocks")
    fun stocks(
        @RequestParam(value = "start") start: String,
        @RequestParam(value = "end") end: String,
        model: Model,
    ): String {
        val results = stockService.findAllByDateBetween(LocalDateTime.parse(start), LocalDateTime.parse(end))
        model.addAttribute("stocks", results)
        return "stock"
    }

    @GetMapping("/stocks/download", produces = ["text/csv"])
    fun stocksCsv(
        @RequestParam(value = "start") start: String,
        @RequestParam(value = "end") end: String,
    ): ResponseEntity<FileSystemResource> {
        val file: File = stockService.writeAllByDateBetween(LocalDateTime.parse(start), LocalDateTime.parse(end))
        return ResponseEntity.ok()
            .header("Content-Disposition", "attachment; filename=stocks.xls")
            .contentLength(file.length())
            .contentType(MediaType.parseMediaType("text/csv"))
            .body(FileSystemResource(file))
    }

    @GetMapping("/stocks/scheduling")
    fun stocksScheduling(
        @RequestParam(value = "start") start: String,
        model: Model,
    ): String {
        val results = apiRequestService.stocks(LocalDateTime.now(), LocalDateTime.parse(start))
        model.addAttribute("stocks", results)
        return "stock"
    }
}
