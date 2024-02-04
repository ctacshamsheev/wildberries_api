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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
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
    fun `for any unauthorized user following URL should redirect to _login page`() {
        mockMvc
            .perform(get("/app/list"))
            .andExpect(status().is3xxRedirection)
    }

    @WithMockUser(username = "some_user", password = "some_password", roles = ["ADMIN", "USER"])
    @Test
    @Order(2)
    fun `method get to _app_list should return empty list`() {
//        assertFalse {
//            sendGetToAppList().andReturn()
//                .response
//                .contentAsString.matches(Regex(testAddress.name!!))
//        }
    }
}