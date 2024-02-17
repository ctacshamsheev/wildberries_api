package com.shamsheev.telegram_service.controller

import com.shamsheev.telegram_service.event.SendMessageEvent
import mu.KotlinLogging
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*


@Controller
@RequestMapping("/telegram")
class TelegramController(
    val publisher: ApplicationEventPublisher,
) {

    @GetMapping("/send")
    fun sendView(
        @RequestBody message: String?,
        model: Model,
    ): String {
        return "send"
    }

    @PostMapping("/send")
    fun send(
        @ModelAttribute("message") message: String?,
    ): String {
        if (message != null) {
            publisher.publishEvent(SendMessageEvent(message))
        }
        return "send"
    }

    companion object {
        val log = KotlinLogging.logger {}
    }
}
