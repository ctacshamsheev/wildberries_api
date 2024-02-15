package com.shamsheev.wildberries.api.statistics.service

import com.shamsheev.wildberries.api.statistics.model.StatisticsData
import com.shamsheev.wildberries.api.statistics.repository.StatisticsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Service
class StatisticsService(
    @Autowired val statistics: StatisticsRepository,
) {

    fun getHeaders(): List<String> {
        val result = mutableListOf<String>("Всего")

        statistics.getHeads()
            .map { it.getSubject() }
            .filterNotNull()
            .forEach { result.add(it) }

        return result
    }

    fun getDates(start: LocalDateTime, end: LocalDateTime): List<LocalDate> {
        val result = mutableListOf<LocalDate>()
        var currentDay: LocalDate = start.toLocalDate()
        val endDay: LocalDate = end.toLocalDate()
        while (currentDay <= endDay) {
            result.add(currentDay)
            currentDay = currentDay.plusDays(1L)
        }
        return result
    }

    fun getStatistics(start: LocalDateTime, end: LocalDateTime): StatisticsData {
        val headers = getHeaders()
        val dates = getDates(start, end)
        val resAll = statistics.getAll(start, end)
        val resGroup = statistics.getGroups(start, end)

        val days = ChronoUnit.DAYS.between(start.toLocalDate(), end.toLocalDate()).toInt() + 1

        val table = Array(days, { Array(headers.size, { 0 }) })

        var groupIndex = 0
        var allIndex = 0
        for ((index, row) in table.withIndex()) {
            if (allIndex < resAll.size && dates[index] == resAll[allIndex].getOrderDate()) {
                row[0] = resAll[allIndex].getCount()!!
                allIndex++
            }

            while (groupIndex < resGroup.size && dates[index] == resGroup[groupIndex].getOrderDate()) {
                val header = resGroup[groupIndex].getSubject()
                row[headers.indexOf(header)] = resGroup[groupIndex].getCount()!!
                groupIndex++
            }
        }

        return StatisticsData(
            headers = headers,
            dates = dates,
            table = table
        )
    }
}
