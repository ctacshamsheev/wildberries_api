package com.shamsheev.wildberries.api.statistics.controller

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
internal class AppControllerTest {
//
//    @Autowired
//    private lateinit var mockMvc: MockMvc
//
//    @Autowired
//    private lateinit var repository: NoteRepository
//
//    private val cookie = Cookie("auth", "${LocalDateTime.now()}")
//
//    private var testNote = Note(name = "NAME", address = "ADDRESS", phone = "+89999999999")
//    private var testNote2 = Note(name = "NAME2", address = "ADDRESS2", phone = "+82222222222")
//
//    @BeforeEach
//    fun cleanBase() {
//        repository.deleteAll()
//    }
//
//    @Disabled
//    @ParameterizedTest
//    @ValueSource(strings = ["/app/add", "/app/list", "/app/0/view", "/app/0/edit", "/app/0/delete"])
//    fun `should login`(url: String) {
//        //given
//        repository.save(testNote)
//        //when
//        mockMvc.perform(
//            MockMvcRequestBuilders.get(url)
//                .flashAttr("note", testNote)
//        )
//            //then
//            .andExpect {
//                MockMvcResultMatchers.status().is3xxRedirection()
//                MockMvcResultMatchers.redirectedUrl("/login")
//            }
//    }
//
//    @Test
//    @WithMockUser(username = "admin", password = "admin", roles = ["ADMIN"])
//    fun add() {
//        //given
//        //when
//        mockMvc.perform(
//            MockMvcRequestBuilders
//                .post("/app/add")
//                .cookie(cookie)
//                .flashAttr("note", testNote)
//        )
//            //then
//            .andExpect {
//                MockMvcResultMatchers.status().is3xxRedirection()
//                MockMvcResultMatchers.redirectedUrl("/app/list")
//            }
//        assertEquals(testNote, repository.getNoteById(testNote.id!!))
//    }
//
//    @Test
//    @WithMockUser(username = "user", password = "user", roles = ["USER"])
//    fun list() {
//        //given
//        repository.save(testNote)
//        repository.save(testNote2)
//        //when
//        mockMvc.perform(
//            MockMvcRequestBuilders
//                .get("/app/list")
//                .cookie(cookie)
//        )
//            //then
//            .andExpect(MockMvcResultMatchers.status().isOk)
//            .andExpect(MockMvcResultMatchers.model().attribute("notes", listOf(testNote, testNote2)))
//    }
//
//    @Test
//    @WithMockUser(username = "user", password = "user", roles = ["USER"])
//    fun view() {
//        //given
//        repository.save(testNote)
//        repository.save(testNote2)
//        //when
//        mockMvc.perform(
//            MockMvcRequestBuilders
//                .get("/app/${testNote.id}/view")
//                .cookie(cookie)
//        )
//            //then
//            .andExpect(MockMvcResultMatchers.status().isOk)
//            .andExpect(MockMvcResultMatchers.model().attribute("note", testNote))
//    }
//
//    @Test
//    @WithMockUser(username = "user", password = "user", roles = ["USER"])
//    fun edit() {
//        //given
//        repository.save(testNote)
//        //when
//        mockMvc.perform(
//            MockMvcRequestBuilders
//                .post("/app/${testNote.id}/edit")
//                .cookie(cookie)
//                .flashAttr("note", testNote2)
//        )
//            //then
//            .andExpect {
//                MockMvcResultMatchers.status().is3xxRedirection()
//                MockMvcResultMatchers.redirectedUrl("/app/0/view")
//            }
//            .andExpect(MockMvcResultMatchers.model().attribute("note", testNote2))
//        assertEquals(testNote2, repository.getNoteById(testNote2.id!!))
//    }
//
//    @Disabled
//    @Test
//    @WithMockUser(username = "user", password = "user", roles = ["USER"])
//    fun deleteNotPermit() {
//        //given
//        testNote = repository.save(testNote)
//        testNote2 = repository.save(testNote2)
//        //when
//        mockMvc.perform(
//            MockMvcRequestBuilders
//                .delete("/app/${testNote.id}/delete")
//                .cookie(cookie)
//        )
//            //then
//            .andExpect {
//                MockMvcResultMatchers.status().`is`(403)
//                MockMvcResultMatchers.redirectedUrl("/login")
//            }
//        assertContentEquals(repository.findAll(), listOf(testNote, testNote2))
//    }
//
//    @Test
//    @WithMockUser(username = "admin", password = "admin", roles = ["ADMIN"])
//    fun delete() {
//        //given
//        testNote = repository.save(testNote)
//        testNote2 = repository.save(testNote2)
//        //when
//        mockMvc.perform(
//            MockMvcRequestBuilders
//                .delete("/app/${testNote.id}/delete")
//                .cookie(cookie)
//        )
//            //then
//            .andExpect {
//                MockMvcResultMatchers.status().is3xxRedirection()
//                MockMvcResultMatchers.redirectedUrl("/app/list")
//            }
//        assertEquals(1, repository.findAll().toList().size)
//        assertEquals(testNote2, repository.getNoteById(testNote2.id!!))
//    }
}