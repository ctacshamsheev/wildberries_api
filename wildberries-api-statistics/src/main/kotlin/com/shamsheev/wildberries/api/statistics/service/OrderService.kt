package com.shamsheev.wildberries.api.statistics.service

import com.shamsheev.wildberries.api.statistics.model.Order
import com.shamsheev.wildberries.api.statistics.model.StatisticsData
import com.shamsheev.wildberries.api.statistics.repository.OrderRepository
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
class OrderService(
    @Autowired val orderRepository: OrderRepository,
    @Autowired val productService: ProductService,
    @Autowired val statisticsService: StatisticsService,
) {
    @Transactional
    fun save(order: Order) {
        order.product = productService.saveIfNotExist(order.product)
        orderRepository.save(order)
    }

    fun save(ordersList: List<Order>) {
        ordersList.forEach(::save)
    }

    fun findAllByDateBetween(start: LocalDateTime, end: LocalDateTime) =
        orderRepository.findAllByDateBetweenOrderByDate(start, end)


    fun writeAllByDateBetween(start: LocalDateTime, end: LocalDateTime) =
        getFileCsv(
            orderRepository.findAllByDateBetweenOrderByDate(start, end),
            statisticsService.getStatistics(start, end)
        )


    private fun getFileCsv(
        ordersList: List<Order>,
        statistics: StatisticsData,
    ): File {
        val workbook = HSSFWorkbook()
        val sheet = workbook.createSheet("order")
        val sheet2 = workbook.createSheet("stat")
        val tempFile = File.createTempFile("file", ".xls")
        setRowsCells(workbook, ordersList, sheet)
        setStats(workbook, statistics, sheet2)
        setAutosizeColumn(sheet, getHeaders().size)
        setHeaderRow(sheet, workbook)
        workbook.write(tempFile.outputStream())
        workbook.close()
        return tempFile
    }

    private fun setRowsCells(
        workbook: HSSFWorkbook,
        ordersList: List<Order>,
        workSheet: HSSFSheet,
    ) {
        val cellStyleDateTime: CellStyle = workbook.createCellStyle()
        val cellStyleDate: CellStyle = workbook.createCellStyle()
        val createHelper: CreationHelper = workbook.creationHelper
        cellStyleDateTime.dataFormat = createHelper.createDataFormat().getFormat("dd.mm.yyyy hh:MM")
        cellStyleDate.dataFormat = createHelper.createDataFormat().getFormat("dd.mm.yyyy")
        for ((index, value) in ordersList.withIndex()) {
            setOrderCells(workSheet, index, value, cellStyleDateTime, cellStyleDate)
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

    private fun setOrderCells(
        workSheet: HSSFSheet,
        index: Int,
        value: Order,
        cellStyleDateTime: CellStyle,
        cellStyleDate: CellStyle,
    ) {
        val row = workSheet.createRow(index + 1)
        var cell = row.createCell(0)
        cell.setCellValue(value.date)
        cell.setCellStyle(cellStyleDateTime)

        cell = row.createCell(1)
        cell.setCellValue(value.lastChangeDate)
        cell.setCellStyle(cellStyleDateTime)

        row.createCell(2).setCellValue(value.warehouseName)
        row.createCell(3).setCellValue(value.countryName)
        row.createCell(4).setCellValue(value.oblastName)
        row.createCell(5).setCellValue(value.regionName)
        row.createCell(6).setCellValue(value.product.supId)
        row.createCell(7).setCellValue(value.product.wbId.toString())
        row.createCell(8).setCellValue(value.product.id)
        row.createCell(9).setCellValue(value.product.category)
        row.createCell(10).setCellValue(value.product.subject)
        row.createCell(11).setCellValue(value.product.brand)
        row.createCell(12).setCellValue(value.product.size)
        row.createCell(13).setCellValue(value.incomeId.toString())
        row.createCell(14).setCellValue(value.isSupply.toString())
        row.createCell(15).setCellValue(value.isRealization.toString())
        row.createCell(16).setCellValue(value.totalPrice.toString())
        row.createCell(17).setCellValue(value.discountPercent.toString())
        row.createCell(18).setCellValue(value.salePrice.toString())
        row.createCell(19).setCellValue(value.finishPrice.toString())
        row.createCell(20).setCellValue(value.priceWithDiscount.toString())
        row.createCell(21).setCellValue(value.isCancel.toString())

        cell = row.createCell(22)
        if (value.cancelDate != null) {
            cell.setCellValue(value.cancelDate)
            cell.setCellStyle(cellStyleDate)
        }
        row.createCell(23).setCellValue(value.orderType.toString())
        row.createCell(24).setCellValue(value.sticker.toString())
        row.createCell(25).setCellValue(value.gNumber.toString())
        row.createCell(26).setCellValue(value.srId.toString())
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
        "Дата и время заказа",
        "Дата и время обновления информации в сервисе",
        "Склад отгрузки",
        "Страна",
        "Округ",
        "Регион",
        "Артикул продавца",
        "Артикул workbook",
        "Баркод",
        "Категория",
        "Предмет",
        "Бренд",
        "Размер товара",
        "Номер поставки",
        "Договор поставки",
        "Договор реализации",
        "Цена без скидок",
        "Скидка продавца",
        "Скидка WB",
        "Фактическая цена с учетом всех скидок",
        "Цена со скидкой продавца",
        "Отмена заказа",
        "Дата и время отмены заказа",
        "Тип заказа",
        "Идентификатор стикера",
        "Номер заказа",
        "Уникальный идентификатор заказа",
    )


    private fun setStats(
        workbook: HSSFWorkbook,
        statistics: StatisticsData,
        workSheet: HSSFSheet,
    ) {
        val headerRow = workSheet.createRow(0)
        val styleWrap = workbook.createCellStyle()
        styleWrap.wrapText = true
        styleWrap.alignment = HorizontalAlignment.CENTER
        styleWrap.verticalAlignment = VerticalAlignment.CENTER

        val cellStyleDate: CellStyle = workbook.createCellStyle()
        val createHelper: CreationHelper = workbook.creationHelper
        cellStyleDate.dataFormat = createHelper.createDataFormat().getFormat("dd.mm.yyyy")

        headerRow.createCell(0).setCellValue("Date")
        workSheet.setColumnWidth(0, 3000)
        for ((index, value) in statistics.headers.withIndex()) {
            val cell = headerRow.createCell(index + 1)
            cell.setCellValue(value)
            cell.setCellStyle(styleWrap)
        }

        var i = 0
        for ((index, value) in statistics.dates.withIndex()) {
            val row = workSheet.createRow(index + 1)
            var cell = row.createCell(0)
            cell.setCellValue(value)
            cell.setCellStyle(cellStyleDate)

            for ((indexC, valueC) in statistics.table[index].withIndex()) {
                valueC
                row.createCell(indexC + 1).setCellValue(valueC.toDouble())
            }
        }
    }
}