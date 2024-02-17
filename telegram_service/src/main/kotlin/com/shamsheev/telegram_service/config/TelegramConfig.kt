package com.shamsheev.telegram_service.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class TelegramConfig {
    @Value("\${telegram.auth.name:undefined}")
    var name: String = "undefined"

    @Value("\${telegram.auth.token:undefined}")
    var token: String = "undefined"
}


