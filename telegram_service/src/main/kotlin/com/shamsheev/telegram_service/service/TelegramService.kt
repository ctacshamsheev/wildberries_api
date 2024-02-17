package com.shamsheev.telegram_service.service

import com.shamsheev.telegram_service.config.TelegramConfig
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update


@Service
class TelegramService(
    @Autowired val telegramConfig: TelegramConfig,
) :
    TelegramLongPollingBot(telegramConfig.token) {

    override fun getBotUsername(): String {
        return telegramConfig.name
    }

    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage() && update.message.hasText()) {
            log.info {
                "пришло сообщение: ${update.message.text}," +
                        " from ${update.message.chat.firstName},  " +
                        "${update.message.chatId.toString()}"
            }
            when (update.message.text) {
                "/start" -> {
                    sendMessage(
                        update, "Привет, ${update.message.chat.firstName}!\n" +
                                "Я телеграмм бот, буду присылать тебе статистику заказов."
                    )
                }
                else -> {
                    sendMessage(
                        update, "${update.message.chat.firstName}, " +
                                "я тебя не понимаю =("
                    )
                }
            }
        }
    }

    private fun sendMessage(update: Update, message: String) {
        val sendMessage = SendMessage()
        sendMessage.chatId = update.message.chatId.toString()
        sendMessage.text = message
        execute(sendMessage)
    }

    fun sendAll(ids: List<String>, message: String) {
        ids.forEach { it ->
            val sendMessage = SendMessage()
            sendMessage.chatId = it
            sendMessage.text = message
            execute(sendMessage)
        }
    }

    companion object {
        val log = KotlinLogging.logger {}
    }
}