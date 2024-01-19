package com.shamsheev.wildberries.api.statistics.controller


import com.shamsheev.wildberries.api.statistics.model.Note
import com.shamsheev.wildberries.api.statistics.repository.NoteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class ApiController(@Autowired val noteRepository: NoteRepository) {

    @GetMapping("/list")
    fun list(@RequestParam name: String?, @RequestParam address: String?, @RequestParam phone: String?): List<Note> {
        if (name != null) return noteRepository.searchNoteByName(name)
        if (address != null) return noteRepository.searchNoteByAddress(address)
        if (phone != null) return noteRepository.searchNoteByPhone(phone)
        return noteRepository.findAll() as List<Note>
    }

    @GetMapping("/{id}/view")
    fun view(@PathVariable id: Long): Note? {
        return noteRepository.getNoteById(id)
    }

    @PostMapping("/add")
    fun add(@RequestBody note: Note) {
        noteRepository.save(note)
    }

    @PutMapping("/{id}/edit")
    fun edit(@RequestBody note: Note, @PathVariable id: Long) {
        note.id = id
        noteRepository.save(note)
    }

    @DeleteMapping("/{id}/delete")
    fun delete(@PathVariable id: Long) {
        noteRepository.deleteById(id)
    }
}