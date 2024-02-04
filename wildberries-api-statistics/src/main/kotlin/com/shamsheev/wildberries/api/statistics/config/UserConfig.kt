package com.shamsheev.wildberries.api.statistics.config

import com.shamsheev.wildberries.api.statistics.model.User
import com.shamsheev.wildberries.api.statistics.repository.UserRepository
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.security.crypto.password.DelegatingPasswordEncoder

@Configuration
class UserConfig(
    val repository: UserRepository,
    val encoder: DelegatingPasswordEncoder,
) {
    @Value("\${wildberries.admin.username:admin}")
    private var username: String = ""

    @Value("\${wildberries.admin.password:admin}")
    private val password: String = ""


    @Bean
    @Primary
    fun createUser(): UserRepository {
        log.info { "!$username! !$password" }
        val user = repository.findByUsername(username)
        if (user == null)
            repository.save(
                User(
                    username = username,
                    password = encoder.encode(password),
                    groups = "ADMIN",
                    credExpired = false,
                    enabled = true,
                    expired = false,
                    locked = false
                )
            )
        return repository
    }

    companion object {
        val log = KotlinLogging.logger {}
    }
}