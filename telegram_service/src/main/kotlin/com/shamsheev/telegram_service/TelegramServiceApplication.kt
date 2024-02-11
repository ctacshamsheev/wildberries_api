package com.shamsheev.telegram_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TelegramServiceApplication

fun main(args: Array<String>) {
    runApplication<TelegramServiceApplication>(*args)
}
