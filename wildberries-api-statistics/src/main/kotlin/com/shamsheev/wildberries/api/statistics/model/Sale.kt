package com.shamsheev.wildberries.api.statistics.model

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

/**
 * @param date Дата и время продажи. Это поле соответствует параметру `dateFrom` в запросе, если параметр `flag`=1. Если часовой пояс не указан, то берется Московское время (UTC+3).
 * @param lastChangeDate Дата и время обновления информации в сервисе. Это поле соответствует параметру `dateFrom` в запросе, если параметр `flag`=0 или не указан. Если часовой пояс не указан, то берется Московское время (UTC+3).
 * @param warehouseName Склад отгрузки
 * @param countryName Страна
 * @param oblastOkrugName Округ
 * @param regionName Регион
 * @param supplierArticle Артикул продавца
 * @param nmId Артикул WB
 * @param barcode Баркод
 * @param category Категория
 * @param subject Предмет
 * @param brand Бренд
 * @param techSize Размер товара
 * @param incomeID Номер поставки
 * @param isSupply Договор поставки
 * @param isRealization Договор реализации
 * @param totalPrice Цена без скидок
 * @param discountPercent Скидка продавца
 * @param spp Скидка WB
 * @param forPay К перечислению продавцу
 * @param finishedPrice Фактическая цена с учетом всех скидок (к взиманию с покупателя)
 * @param priceWithDisc Цена со скидкой продавца, от которой считается сумма к перечислению продавцу `forPay` (= `totalPrice` * (1 - `discountPercent`/100))
 * @param saleID Уникальный идентификатор продажи/возврата <ul>  <li> `S**********` — продажа  <li> `R**********` — возврат (на склад WB)  </ul>
 * @param orderType Тип заказа <ul> <li> `Клиентский` — заказ, поступивший от покупателя <li> `Возврат Брака` — возврат товара продавцу <li> `Принудительный возврат` — возврат товара продавцу <li> `Возврат обезлички` — возврат товара продавцу <li> `Возврат Неверного Вложения` — возврат товара продавцу <li> `Возврат Продавца` — возврат товара продавцу </ul>
 * @param sticker Идентификатор стикера
 * @param gNumber Номер заказа
 * @param srid Уникальный идентификатор заказа.<br> Примечание для использующих API Маркетплейс: `srid` равен `rid` в ответах методов сборочных заданий.
 */

@Entity
@Table(name = "sale_table")
data class Sale(
    @Id
    @Column(name = "sr_id")
    val srId: String? = null,

    @Column(name = "order_date")
    val date: LocalDateTime? = null,

    @Column(name = "last_change_date")
    val lastChangeDate: LocalDateTime? = null,

    @Column(name = "warehouse_name")
    val warehouseName: String? = null,

    @Column(name = "country_name")
    val countryName: String? = null,

    @Column(name = "oblast_name")
    val oblastName: String? = null,

    @Column(name = "region_name")
    val regionName: String? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    var product: Product,

    @Column(name = "income_id")
    val incomeId: Int? = null,

    @Column(name = "is_supply")
    val isSupply: Boolean? = null,

    @Column(name = "is_realization")
    val isRealization: Boolean? = null,

    @Column(name = "total_price")
    val totalPrice: BigDecimal? = null,

    @Column(name = "discount_percent")
    val discountPercent: Int? = null,

    @Column(name = "sale_price")
    val salePrice: BigDecimal? = null,

    @Column(name = "for_pay")
    val forPay: BigDecimal? = null,

    @Column(name = "finish_Price")
    val finishPrice: BigDecimal? = null,

    @Column(name = "price_with_discount")
    val priceWithDiscount: BigDecimal? = null,

    @Column(name = "sale_id")
    val saleId: String? = null,

    @Column(name = "order_type")
    val orderType: String? = null,

    @Column(name = "sticker")
    val sticker: String? = null,

    @Column(name = "g_number")
    val gNumber: String? = null,

    )