package com.shamsheev.wildberries.api.statistics.model

import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

/**
 * @param incomeId Номер поставки
 * @param number Номер УПД
 * @param date Дата поступления. Если часовой пояс не указан, то берется Московское время UTC+3.
 * @param lastChangeDate Дата и время обновления информации в сервисе. Это поле соответствует параметру `dateFrom` в запросе. Если часовой пояс не указан, то берется Московское время UTC+3.
 * @param supplierArticle Артикул продавца
 * @param techSize Размер товара (пример S, M, L, XL, 42, 42-43)
 * @param barcode Бар-код
 * @param quantity Количество
 * @param totalPrice Цена из УПД
 * @param dateClose Дата принятия (закрытия) в WB. Если часовой пояс не указан, то берется Московское время UTC+3.
 * @param warehouseName Название склада
 * @param nmId Артикул WB
 * @param status Текущий статус поставки
 */

@Entity
@Table(name = "income_table")
data class Income(
    @Id
    @GeneratedValue
    var id: Long? = null,

    @Column(name = "income_id")
    val incomeId: Int? = null,

    @Column(name = "number")
    val number: String? = null,

    @Column(name = "date_in")
    val date: LocalDate? = null,

    @Column(name = "last_change_date")
    val lastChangeDate: LocalDateTime? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    var product: Product,

    @Column(name = "quantity")
    val quantity: Int? = null,

    @Column(name = "total_price")
    val totalPrice: BigDecimal? = null,

    @Column(name = "date_close")
    val dateClose: LocalDate? = null,

    @Column(name = "warehouse_name")
    val warehouseName: String? = null,

    @Column(name = "status")
    val status: String? = null,
)
