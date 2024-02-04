package com.shamsheev.wildberries.api.statistics.controller

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
internal class ApiControllerTest {
//
//    @LocalServerPort
//    private var port: Int = 0
//
//    @Autowired
//    private lateinit var restTemplate: TestRestTemplate
//
//    @Autowired
//    private lateinit var repository: NoteRepository
//
//    private fun url(s: String) = "http://localhost:${port}/${s}"
//
//    private val headers = HttpHeaders().also { it.add("Cookie", "auth=${LocalDateTime.now()}") }
//    private var testNote = Note(name = "NAME", address = "ADDRESS", phone = "+89999999999")
//    private var testNote2 = Note(name = "NAME2", address = "ADDRESS2", phone = "+82222222222")
//
//    @BeforeEach
//    fun cleanBase() {
//        repository.deleteAll()
//    }
//
//    @Test
//    fun add() {
//        //given
//        //when
//        val resp = restTemplate
//            .withBasicAuth("api", "api")
//            .exchange(
//                url("/api/add"),
//                HttpMethod.POST,
//                HttpEntity(testNote, headers),
//                Unit::class.java
//            )
//        //then
//        assertEquals(resp.statusCode, HttpStatus.OK)
//
//        val result = repository.findAll().first()
//        assertEquals(result.name, testNote.name)
//    }
//
//    @Test
//    fun list() {
//        //given
//        repository.save(testNote)
//        repository.save(testNote2)
//        //when
//        val resp = restTemplate
//            .withBasicAuth("admin", "admin")
//            .exchange(
//                url("/api/list"),
//                HttpMethod.GET,
//                HttpEntity(null, headers),
//                Array<Note>::class.java
//            )
//        //then
//        assertEquals(resp.statusCode, HttpStatus.OK)
//        assertContentEquals(resp.body?.toList(), listOf(testNote, testNote2))
//    }
//
//    @Test
//    fun view() {
//        //given
//        testNote = repository.save(testNote)
//        testNote2 = repository.save(testNote2)
//        //when
//        val resp = restTemplate
//            .withBasicAuth("api", "api")
//            .exchange(
//                url("/api/${testNote2.id}/view"),
//                HttpMethod.GET,
//                HttpEntity(null, headers),
//                Note::class.java
//            )
//        //then
//        assertEquals(resp.statusCode, HttpStatus.OK)
//        assertEquals(resp.body, testNote2)
//    }
//
//
//    @Test
//    fun edit() {
//        //given
//        testNote = repository.save(testNote)
//        //when
//        val resp = restTemplate
//            .withBasicAuth("admin", "admin")
//            .exchange(
//                url("/api/${testNote.id}/edit"),
//                HttpMethod.PUT,
//                HttpEntity(testNote2, headers),
//                Unit::class.java
//            )
//        //then
//        assertEquals(resp.statusCode, HttpStatus.OK)
//        val result = repository.getNoteById(testNote.id!!)
//        assertEquals(result.name, testNote2.name)
//    }
//
//    @Test
//    @Disabled
//    fun deleteNotPermit() {
//        //given
//        testNote = repository.save(testNote)
//        //when
//        val resp = restTemplate
//            .withBasicAuth("user", "user")
//            .exchange(
//                url("/api/${testNote.id}/delete"),
//                HttpMethod.DELETE,
//                HttpEntity(null, headers),
//                Unit::class.java
//            )
//        //then
//        assertNotEquals(resp.statusCode, HttpStatus.OK)
//        assertContentEquals(listOf(testNote), repository.findAll())
//    }
//
//    @Test
//    fun delete() {
//        //given
//        testNote = repository.save(testNote)
//        //when
//        val resp = restTemplate
//            .withBasicAuth("admin", "admin")
//            .exchange(
//                url("/api/${testNote.id}/delete"),
//                HttpMethod.DELETE,
//                HttpEntity(null, headers),
//                Unit::class.java
//            )
//        //then
//        assertEquals(resp.statusCode, HttpStatus.OK)
//        assertContentEquals(emptyList(), repository.findAll())
//    }
//
//    @Disabled
//    @ParameterizedTest
//    @ValueSource(strings = ["/api/add", "/api/list", "/api/0/view", "/api/0/edit", "/api/0/delete"])
//    fun `should login`(url: String) {
//        //given
//        repository.save(testNote)
//        //when
//        val resp = restTemplate.exchange(
//            url(url),
//            HttpMethod.GET,
//            HttpEntity(null, null),
//            String::class.java
//        )
//        //then
//        assertEquals(resp.statusCode, HttpStatus.OK)
//        assertTrue(resp.body.toString().contains("password"))
//    }

}