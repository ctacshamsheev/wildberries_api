package com.shamsheev.wildberries.api.statistics.controller


import com.shamsheev.wildberries.api.statistics.model.Note
import com.shamsheev.wildberries.api.statistics.repository.NoteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/app")
class AppController(@Autowired val noteRepository: NoteRepository) {

    @GetMapping("/list")
    fun list(
        @RequestParam name: String?,
        @RequestParam address: String?,
        @RequestParam phone: String?,
        model: Model,
    ): String {
        val notes: List<Note> =
            if (name != null) noteRepository.searchNoteByName(name)
            else if (address != null) noteRepository.searchNoteByAddress(address)
            else if (phone != null) noteRepository.searchNoteByPhone(phone)
            else noteRepository.findAll() as List<Note>
        model.addAttribute("notes", notes)
        return "list"
    }

    @GetMapping("/{id}/view")
    fun view(@PathVariable id: Long, model: Model): String {
        model.addAttribute("note", noteRepository.getNoteById(id))
        return "view"
    }

    @GetMapping("/add")
    fun addView(@RequestBody note: Note?, model: Model): String {
        model.addAttribute("note", note)
        return "add"
    }

    @PostMapping("/add")
    fun add(@ModelAttribute("note") note: Note): String {
        noteRepository.save(note)
        return "redirect:/app/list"
    }

    @GetMapping("/{id}/edit")
    fun editView(@PathVariable id: Long, model: Model): String {
        model.addAttribute("note", noteRepository.getNoteById(id))
        return "edit"
    }

    @PostMapping("/{id}/edit")
    fun edit(@PathVariable id: Long, @ModelAttribute("note") note: Note, model: Model): String {
        note.id = id
        model.addAttribute("note", note)
        noteRepository.save(note)
        return "redirect:/app/$id/view"
    }

    @DeleteMapping("/{id}/delete")
    fun delete(@PathVariable id: Long, model: Model): String {
        noteRepository.deleteById(id)
        return "redirect:/app/list"
    }
}
