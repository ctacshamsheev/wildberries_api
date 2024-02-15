package com.shamsheev.wildberries.api.statistics.repository

import com.shamsheev.wildberries.api.statistics.model.Order
import com.shamsheev.wildberries.api.statistics.model.StatisticsDto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface StatisticsRepository : JpaRepository<Order, String> {

    @Query(
        value = """
            select DATE(order_date) as orderDate,  count(*) as count
            from order_table
                inner join product_table pt on pt.id = order_table.product_id
                where order_date BETWEEN ? AND ?
            group by DATE(order_date)
            order by DATE(order_date);
        """, nativeQuery = true
    )
    fun getAll(start: LocalDateTime, end: LocalDateTime): List<StatisticsDto>


    @Query(
        value = """
            select DATE(order_date) as orderDate,  count(*) as count,  pt.subject as subject
            from order_table
                inner join product_table pt on pt.id = order_table.product_id
                where order_date BETWEEN ? AND ?
            group by DATE(order_date) , pt.subject
            order by DATE(order_date), subject;
        """, nativeQuery = true
    )
    fun getGroups(start: LocalDateTime, end: LocalDateTime): List<StatisticsDto>

    @Query(
        value = """
        select subject
        from product_table
        group by subject
        having subject is not null
        order by subject
    """, nativeQuery = true
    )
    fun getHeads(): List<StatisticsDto>
}