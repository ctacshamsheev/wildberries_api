package com.shamsheev.wildberries.api.statistics.controller

import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
internal class WbStatisticsControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

//    private val testAddress = Address(name = "Pam Beesly", city = "Scampton", phone = "79990000000")

    @Test
    @Order(1)
    fun notAuthorize() {
        mockMvc
            .perform(get("/"))
            .andExpect(status().is3xxRedirection)
    }


    @Test
    @WithMockUser(username = "user", password = "user", roles = ["USER"])
    fun index() {
        //given
        //when
        mockMvc.perform(
            MockMvcRequestBuilders
                .get("/")
        )
            //then
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    @WithMockUser(username = "user", password = "user", authorities = ["USER"])
    fun scheduler() {
        //given
        //when
        mockMvc.perform(
            get("/scheduling/results")
        )
            //then
            .andExpect(MockMvcResultMatchers.status().is4xxClientError)
    }
}