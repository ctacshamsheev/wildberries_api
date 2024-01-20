package com.shamsheev.wildberries.api.statistics.config

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import openapi.wildberries.ru.statistics.apis.DefaultApi
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.IOException

@Configuration
class StatisticsApi {
    @Value("\${wildberries.statistics.url}")
    private var url: String = "default_url"

    @Value("\${wildberries.statistics.auth.token}")
    private var accessToken: String = "default_token"

    var proxyAuthenticator: okhttp3.Authenticator = object : okhttp3.Authenticator {
        @Throws(IOException::class)
        override fun authenticate(route: Route?, response: Response): Request {
            return response.request
                .newBuilder()
                .addHeader("Authorization", "Bearer $accessToken")
                .build()
        }
    }

    @Bean
    @Qualifier("statisticsApi")
    fun api(): DefaultApi {
        val client = OkHttpClient.Builder()
            .authenticator(proxyAuthenticator)
            .build()
        return DefaultApi(url, client = client)
    }
}