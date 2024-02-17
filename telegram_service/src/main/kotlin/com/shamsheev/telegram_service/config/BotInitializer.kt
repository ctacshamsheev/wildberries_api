package com.shamsheev.telegram_service.config

import com.shamsheev.telegram_service.event.SendMessageEvent
import com.shamsheev.telegram_service.service.TelegramService
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.event.ApplicationContextEvent
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

@Component
class BotInitializer(
    @Autowired
    val telegramService: TelegramService,
) {

    @EventListener
    fun handle(event: ContextRefreshedEvent) {
        val telegramBotsApi = TelegramBotsApi(DefaultBotSession::class.java)

        telegramBotsApi.registerBot(telegramService)
    }

    @EventListener
    fun handle(event: SendMessageEvent) {
        telegramService.sendAll(listOf("389886877", ""), "Hello")
    }

    @EventListener
    fun handle(event: ApplicationContextEvent) {
        log.info { "context start" }
        telegramService.sendAll(listOf("389886877", ""), "context start")
    }

    companion object {
        val log = KotlinLogging.logger {}
    }

}