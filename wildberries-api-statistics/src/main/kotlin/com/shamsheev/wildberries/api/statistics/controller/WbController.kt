package com.shamsheev.wildberries.api.statistics.controller

import com.shamsheev.wildberries.api.statistics.repository.NoteRepository
import kotlintest.org.openapitools.client.api.PetApi
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import org.openapitools.client.infrastructure.ApiClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*
import java.io.IOException


@RestController
@RequestMapping("/wb")
class WbController(@Autowired val noteRepository: NoteRepository) {

    @Value("\${auth.token}")
    private lateinit var accessToken: String

    var proxyAuthenticator: okhttp3.Authenticator = object : okhttp3.Authenticator {
        @Throws(IOException::class)
        override fun authenticate(route: Route?, response: Response): Request? {
            return response.request
                .newBuilder()
                .addHeader("Authorization", "Bearer " + accessToken)
                .build()
        }
    }

    @GetMapping("/hello")
    fun list(): String {

        try {
            val client = ApiClient.builder
                .proxyAuthenticator(proxyAuthenticator)
                .build()

            val api = PetApi(client = client)
            val results = api.findPetsByStatus(arrayListOf(PetApi.StatusFindPetsByStatus.sold))
            println(results.toString())

            results
                .stream()
                .map { e -> e.name }
                .forEach { e -> println(e) }
        } catch (ex: Exception) {
            println(
                ex.message + ex
            )
        }
        return "hello"
    }
}