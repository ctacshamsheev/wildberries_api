package com.shamsheev.wildberries.api.statistics.controller

import com.shamsheev.wildberries.api.statistics.repository.NoteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/wb")
class WbController(@Autowired val noteRepository: NoteRepository) {

    @GetMapping("/hello")
    fun list(): String {
        return "hello"
    }
}