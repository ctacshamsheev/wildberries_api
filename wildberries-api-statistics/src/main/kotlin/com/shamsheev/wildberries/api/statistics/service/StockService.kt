package com.shamsheev.wildberries.api.statistics.service

import com.shamsheev.wildberries.api.statistics.model.Stock
import com.shamsheev.wildberries.api.statistics.repository.StockRepository
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.CreationHelper
import org.apache.poi.ss.usermodel.HorizontalAlignment
import org.apache.poi.ss.usermodel.VerticalAlignment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.File
import java.time.LocalDateTime
import javax.transaction.Transactional

@Service
class StockService(
    @Autowired val stockRepository: StockRepository,
    @Autowired val productService: ProductService,
) {
    @Transactional
    fun save(stock: Stock) {
        stock.product = productService.saveIfNotExist(stock.product)
        stockRepository.save(stock)
    }

    @Transactional
    fun save(stockList: List<Stock>) {
        stockList.forEach(::save)
    }

    fun findAllByDateBetween(start: LocalDateTime, end: LocalDateTime) =
        stockRepository.findAllByLastChangeDateBetweenOrderByWarehouseName(start, end)

    fun writeAllByDateBetween(start: LocalDateTime, end: LocalDateTime) =
        getFileCsv(stockRepository.findAllByLastChangeDateBetweenOrderByWarehouseName(start, end))


    private fun getFileCsv(salesList: List<Stock>): File {
        val workbook = HSSFWorkbook()
        val sheet = workbook.createSheet()
        val tempFile = File.createTempFile("file", ".xls")
        setRowsCells(workbook, salesList, sheet)
        setAutosizeColumn(sheet, getHeaders().size)
        setHeaderRow(sheet, workbook)
        workbook.write(tempFile.outputStream())
        workbook.close()
        return tempFile
    }

    private fun setRowsCells(
        workbook: HSSFWorkbook,
        ordersList: List<Stock>,
        workSheet: HSSFSheet,
    ) {
        val cellStyleDateTime: CellStyle = workbook.createCellStyle()
        val cellStyleDate: CellStyle = workbook.createCellStyle()
        val createHelper: CreationHelper = workbook.creationHelper
        cellStyleDate.dataFormat = createHelper.createDataFormat().getFormat("dd.mm.yyyy")
        cellStyleDateTime.dataFormat = createHelper.createDataFormat().getFormat("dd.mm.yyyy hh:MM")
        for ((index, value) in ordersList.withIndex()) {
            setStockCells(workSheet, index, value, cellStyleDateTime, cellStyleDate)
        }
    }

    private fun setAutosizeColumn(workSheet: HSSFSheet, size: Int) {
        try {
            for (j in 0..size) {
                workSheet.autoSizeColumn(j)
            }
        } catch (e: Throwable) {
        }
    }

    private fun setStockCells(
        workSheet: HSSFSheet,
        index: Int,
        value: Stock,
        cellStyleDateTime: CellStyle,
        cellStyleDate: CellStyle,
    ) {
        val row = workSheet.createRow(index + 1)
        var cell = row.createCell(0)
        cell.setCellValue(value.lastChangeDate)
        cell.setCellStyle(cellStyleDateTime)

        row.createCell(1).setCellValue(value.warehouseName)
        row.createCell(2).setCellValue(value.product.supId)
        row.createCell(3).setCellValue(value.product.wbId.toString())
        row.createCell(4).setCellValue(value.product.id)

        row.createCell(5).setCellValue(value.quantity?.toDouble() ?: 0.0)
        row.createCell(6).setCellValue(value.inWayToClient?.toDouble() ?: 0.0)
        row.createCell(7).setCellValue(value.inWayFromClient?.toDouble() ?: 0.0)
        row.createCell(8).setCellValue(value.quantityFull?.toDouble() ?: 0.0)

        row.createCell(9).setCellValue(value.product.category)
        row.createCell(10).setCellValue(value.product.subject)
        row.createCell(11).setCellValue(value.product.brand)
        row.createCell(12).setCellValue(value.product.size)

        row.createCell(13).setCellValue(value.price.toString())
        row.createCell(14).setCellValue(value.discount.toString())
        row.createCell(15).setCellValue(value.isSupply.toString())
        row.createCell(16).setCellValue(value.isRealization.toString())
        row.createCell(17).setCellValue(value.scCode.toString())
    }

    private fun setHeaderRow(
        workSheet: HSSFSheet,
        workbook: HSSFWorkbook,
    ) {
        val headerRow = workSheet.createRow(0)
        val styleWrap = workbook.createCellStyle()
        styleWrap.wrapText = true
        styleWrap.alignment = HorizontalAlignment.CENTER
        styleWrap.verticalAlignment = VerticalAlignment.CENTER

        for ((index, value) in getHeaders().withIndex()) {
            val cell = headerRow.createCell(index)
            cell.setCellValue(value)
            cell.setCellStyle(styleWrap)
            if (workSheet.getColumnWidth(index) < 3000) {
                workSheet.setColumnWidth(index, 3000)
            }
        }
    }

    fun getHeaders() = listOf(
        "Дата и время обновления:",
        "Склад отгрузки:",
        "Артикул продавца:",
        "Артикул WB:",
        "Баркод:",
        "Количество, доступное для продажи:",
        "В пути к клиенту:",
        "В пути от клиента:",
        "Полное (непроданное) количество:",
        "Категория:",
        "Предмет:",
        "Бренд:",
        "Размер товара:",
        "Цена:",
        "Скидка:",
        "Договор поставки:",
        "Договор реализации:",
        "Код контракта:",

        )
}