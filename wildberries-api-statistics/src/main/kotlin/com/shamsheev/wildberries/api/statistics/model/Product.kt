package com.shamsheev.wildberries.api.statistics.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 * @param barcode Баркод
 * @param supplierArticle Артикул продавца
 * @param nmId Артикул WB
 * @param category Категория
 * @param subject Предмет
 * @param brand Бренд
 * @param techSize Размер товара
 */

@Entity
@Table(name = "product_table")
data class Product(
    @Id
    @Column(name = "id")
    var id: Long,

    @Column(name = "sup_id")
    var supId: String,

    @Column(name = "wb_id")
    var wbId: Long,

    @Column(name = "category")
    var category: String?,

    @Column(name = "subject")
    var subject: String,

    @Column(name = "brand")
    var brand: String,

    @Column(name = "size")
    var size: String,
)