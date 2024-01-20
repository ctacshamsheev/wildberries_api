package com.shamsheev.wildberries.api.statistics

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan
//import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@ServletComponentScan
//@EnableFeignClients
class WildberriesApiStatisticsApplication

fun main(args: Array<String>) {
    runApplication<WildberriesApiStatisticsApplication>(*args)
}
