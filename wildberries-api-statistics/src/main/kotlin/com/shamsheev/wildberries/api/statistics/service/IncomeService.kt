package com.shamsheev.wildberries.api.statistics.service

import com.shamsheev.wildberries.api.statistics.model.Income
import com.shamsheev.wildberries.api.statistics.repository.IncomeRepository
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
class IncomeService(
    @Autowired
    val incomeRepository: IncomeRepository,
    @Autowired
    val productService: ProductService,
) {
    @Transactional
    fun save(income: Income) {
        income.product = productService.saveIfNotExist(income.product)
        if (!incomeRepository.existsByIncomeIdAndDateAndProduct(
                incomeId = income.incomeId,
                date = income.date,
                product = income.product
            )
        ) {
            incomeRepository.save(income)
        }
    }

    fun save(incomesList: List<Income>) {
        incomesList.forEach(::save)
    }

    fun findAllByDateBetween(start: LocalDateTime, end: LocalDateTime) =
        incomeRepository.findAllByDateBetweenOrderByDate(start.toLocalDate(), end.toLocalDate())

    fun writeAllByDateBetween(start: LocalDateTime, end: LocalDateTime) =
        getFileCsv(findAllByDateBetween(start, end))

    private fun getFileCsv(ordersList: List<Income>): File {
        val workbook = HSSFWorkbook()
        val sheet = workbook.createSheet()
        val tempFile = File.createTempFile("file", ".xls")
        setRowsCells(workbook, ordersList, sheet)
        setAutosizeColumn(sheet)
        setHeaderRow(sheet, workbook)
        workbook.write(tempFile.outputStream())
        workbook.close()
        return tempFile
    }

    private fun setRowsCells(
        workbook: HSSFWorkbook,
        ordersList: List<Income>,
        workSheet: HSSFSheet,
    ) {
        val cellStyleDateTime: CellStyle = workbook.createCellStyle()
        val cellStyleDate: CellStyle = workbook.createCellStyle()
        val createHelper: CreationHelper = workbook.creationHelper
        cellStyleDateTime.dataFormat = createHelper.createDataFormat().getFormat("dd.mm.yyyy hh:MM")
        cellStyleDate.dataFormat = createHelper.createDataFormat().getFormat("dd.mm.yyyy")
        for ((index, value) in ordersList.withIndex()) {
            setIncomeCells(workSheet, index, value, cellStyleDateTime, cellStyleDate)
        }
    }

    private fun setAutosizeColumn(workSheet: HSSFSheet) {
        for (j in 0..26) {
            workSheet.autoSizeColumn(j)
        }
    }

    private fun setIncomeCells(
        workSheet: HSSFSheet,
        index: Int,
        value: Income,
        cellStyleDateTime: CellStyle,
        cellStyleDate: CellStyle,
    ) {
        val row = workSheet.createRow(index + 1)

        row.createCell(0).setCellValue(value.incomeId.toString())
        row.createCell(1).setCellValue(value.number)

        var cell = row.createCell(2)
        cell.setCellValue(value.date)
        cell.setCellStyle(cellStyleDateTime)

        cell = row.createCell(3)
        cell.setCellValue(value.lastChangeDate)
        cell.setCellStyle(cellStyleDateTime)

        row.createCell(4).setCellValue(value.product.supId)
        row.createCell(5).setCellValue(value.product.wbId.toString())
        row.createCell(6).setCellValue(value.product.id)
        row.createCell(7).setCellValue(value.product.category)
        row.createCell(8).setCellValue(value.product.subject)
        row.createCell(9).setCellValue(value.product.brand)
        row.createCell(10).setCellValue(value.product.size)

        row.createCell(11).setCellValue(value.quantity.toString())
        row.createCell(12).setCellValue(value.totalPrice.toString())
        row.createCell(13).setCellValue(value.dateClose.toString())
        row.createCell(14).setCellValue(value.warehouseName.toString())
        row.createCell(15).setCellValue(value.status.toString())
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
        "Номер поставки:",
        "Номер УПД:",
        "Дата поступления:",
        "Дата и время обновления:",
        "Артикул продавца:",
        "Артикул WB:",
        "Баркод:",
        "Категория:",
        "Предмет:",
        "Бренд:",
        "Размер товара:",
        "Количество:",
        "Цена из УПД:",
        "Дата принятия (закрытия) в WB:",
        "Название склада:",
        "Текущий статус поставки:",
    )

}