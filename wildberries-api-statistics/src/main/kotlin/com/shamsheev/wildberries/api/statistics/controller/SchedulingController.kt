package com.shamsheev.wildberries.api.statistics.controller

import com.shamsheev.wildberries.api.statistics.service.*
import mu.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@Controller
@RequestMapping("/scheduling")
class SchedulingController(
    val apiRequestResultService: ApiRequestResultService,
) {

    @GetMapping("/results")
    fun orders(
        @RequestParam(value = "start") start: String,
        @RequestParam(value = "end") end: String,
        model: Model,
    ): String {
        val results = apiRequestResultService.findAllByDateBetween(LocalDateTime.parse(start), LocalDateTime.parse(end))
        model.addAttribute("results", results)
        return "scheduling_results"
    }

    companion object {
        val log = KotlinLogging.logger {}
    }
}
