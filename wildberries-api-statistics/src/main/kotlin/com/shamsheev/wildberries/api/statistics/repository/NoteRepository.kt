package com.shamsheev.wildberries.api.statistics.repository

import com.shamsheev.wildberries.api.statistics.model.Note
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface NoteRepository : CrudRepository<Note, Long> {

    fun getNoteById(id: Long): Note

    fun searchNoteByName(s: String): List<Note>

    fun searchNoteByAddress(s: String): List<Note>

    fun searchNoteByPhone(s: String): List<Note>
}