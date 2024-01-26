package com.shamsheev.wildberries.api.statistics.model

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

/**
 * @param lastChangeDate Дата и время обновления информации в сервисе. Это поле соответствует параметру `dateFrom` в запросе. Если часовой пояс не указан, то берется Московское время (UTC+3).
 * @param warehouseName Название склада
 * @param supplierArticle Артикул продавца
 * @param nmId Артикул WB
 * @param barcode Баркод
 * @param quantity Количество, доступное для продажи (сколько можно добавить в корзину)
 * @param inWayToClient В пути к клиенту
 * @param inWayFromClient В пути от клиента
 * @param quantityFull Полное (непроданное) количество, которое числится за складом (= `quantity` + в пути)
 * @param category Категория
 * @param subject Предмет
 * @param brand Бренд
 * @param techSize Размер
 * @param price Цена
 * @param discount Скидка
 * @param isSupply Договор поставки (внутренние технологические данные)
 * @param isRealization Договор реализации (внутренние технологические данные)
 * @param scCode Код контракта (внутренние технологические данные)
 */

@Entity
@Table(name = "stock_table")
data class Stock(
    @Id
    @GeneratedValue
    var id: Long? = null,

    @Column(name = "last_change_date")
    val lastChangeDate: LocalDateTime? = null,

    @Column(name = "warehouse_name")
    val warehouseName: String? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    var product: Product,

    @Column(name = "quantity")
    val quantity: Int? = null,

    @Column(name = "in_way_to_client")
    val inWayToClient: Int? = null,

    @Column(name = "in_way_from_client")
    val inWayFromClient: Int? = null,

    @Column(name = "quantity_full")
    val quantityFull: Int? = null,

    @Column(name = "price")
    val price: BigDecimal? = null,

    @Column(name = "discount")
    val discount: BigDecimal? = null,

    @Column(name = "is_supply")
    val isSupply: Boolean? = null,

    @Column(name = "is_realization")
    val isRealization: Boolean? = null,

    @Column(name = "sc_code")
    val scCode: String? = null,
)
