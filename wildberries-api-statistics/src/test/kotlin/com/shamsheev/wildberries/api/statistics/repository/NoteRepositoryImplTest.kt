package com.shamsheev.wildberries.api.statistics.repository

import com.shamsheev.wildberries.api.statistics.model.Note
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals


@DataJpaTest
@ActiveProfiles("test")
internal class NoteRepositoryImplTest {

    @Autowired
    private lateinit var repository: NoteRepository

    @Test
    fun saveAndGetById() {
        //given
        val note = Note(name = "name1", address = "address1", phone = "phone1")
        repository.save(note)
        //when
        val result = repository.getNoteById(note.id!!)
        //then
        assertEquals(note, result)
    }

    @Test
    fun getAll() {
        //given
        val note1 = Note(name = "name1", address = "address1", phone = "phone1")
        val note2 = Note(name = "name2", address = "address2", phone = "phone2")
        repository.save(note1)
        repository.save(note2)
        //when
        val result = repository.findAll()
        //then
        assertContentEquals(listOf(note1, note2), result)
    }

    @Test
    fun searchByName() {
        //given
        val note1 = Note(name = "name1", address = "address1", phone = "phone1")
        val note2 = Note(name = "name2", address = "address2", phone = "phone2")
        repository.save(note1)
        repository.save(note2)
        //when
        val result = repository.searchNoteByName("name2")
        //then
        assertEquals(listOf(note2), result)
    }

    @Test
    fun searchByAddress() {
        //given
        val note1 = Note(name = "name1", address = "address", phone = "phone1")
        val note2 = Note(name = "name2", address = "address", phone = "phone2")
        repository.save(note1)
        repository.save(note2)
        //when
        val result = repository.searchNoteByAddress("address")
        //then
        assertEquals(listOf(note1, note2), result)
    }

    @Test
    fun searchByPhone() {
        //given
        val note1 = Note(name = "name1", address = "address", phone = "phone1")
        val note2 = Note(name = "name2", address = "address", phone = "phone2")
        repository.save(note1)
        repository.save(note2)
        //when
        val result = repository.searchNoteByPhone("phone3")
        //then
        assertEquals(listOf(), result)
    }

    @Test
    fun deleteById() {
        //given
        val note1 = Note(name = "name1", address = "address1", phone = "phone1")
        val note2 = Note(name = "name2", address = "address2", phone = "phone2")
        repository.save(note1)
        repository.save(note2)
        //when
        repository.deleteById(note1.id!!)
        //then
        val result = repository.findAll()
        assertContentEquals(listOf(note2), result)
    }

    @Test
    fun updateById() {
        //given
        val note1 = Note(name = "name1", address = "address1", phone = "phone1")
        repository.save(note1)
        //when
        val note2 = Note(name = "name2", address = "address2", phone = "phone2")
        note2.id = note1.id
        repository.save(note2)
        //then
        val result = repository.getNoteById(note1.id!!)
        assertEquals(note2, result)
    }
}