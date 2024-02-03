package com.shamsheev.wildberries.api.statistics.controller

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
import java.io.File
import java.time.LocalDateTime


@Controller
@RequestMapping("/wildberries/statistics")
class WbStatisticsController(
//    val wbStatistics: WbStatistics,
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
    ): ResponseEntity<FileSystemResource> {
        val file: File = orderService.writeAllByDateBetween(LocalDateTime.parse(start), LocalDateTime.parse(end))
        return ResponseEntity.ok()
            .header("Content-Disposition", "attachment; filename=orders.xls")
            .contentLength(file.length())
            .contentType(MediaType.parseMediaType("text/csv"))
            .body(FileSystemResource(file))
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
            .body(FileSystemResource(file))
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
        model: Model,
    ): ResponseEntity<FileSystemResource> {
        val file: File = incomeService.writeAllByDateBetween(LocalDateTime.parse(start), LocalDateTime.parse(end))
        return ResponseEntity.ok()
            .header("Content-Disposition", "attachment; filename=incomes.xls")
            .contentLength(file.length())
            .contentType(MediaType.parseMediaType("text/csv"))
            .body(FileSystemResource(file))
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
        model: Model,
    ): ResponseEntity<FileSystemResource> {
        val file: File = stockService.writeAllByDateBetween(LocalDateTime.parse(start), LocalDateTime.parse(end))
        return ResponseEntity.ok()
            .header("Content-Disposition", "attachment; filename=stocks.xls")
            .contentLength(file.length())
            .contentType(MediaType.parseMediaType("text/csv"))
            .body(FileSystemResource(file))
    }
//
//    @ExceptionHandler(Exception::class)
//    fun handleError(req: HttpServletRequest, ex: Exception, model: Model): String {
//        log.error("Request: " + req.requestURL + " raised " + ex.message + ", " + ex)
//        model.addAttribute("message", ex.message)
//        model.addAttribute("exception", ex.toString())
//        return "error"
//    }

    companion object {
        val log = KotlinLogging.logger {}
    }
}
