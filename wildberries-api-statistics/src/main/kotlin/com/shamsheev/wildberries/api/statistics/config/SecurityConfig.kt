package com.shamsheev.wildberries.api.statistics.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.DelegatingPasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun formLoginFilterChain(http: HttpSecurity): SecurityFilterChain {

        http {
            authorizeRequests {
                authorize("/scheduling/**", hasAuthority("ADMIN"))
                authorize(anyRequest, authenticated)
            }
            formLogin { permitAll() }
            csrf {
                ignoringAntMatchers("/rest/**", "/login")
            }
            headers { frameOptions { sameOrigin = true } }
        }
        return http.build()
    }

    @Bean
    @Primary
    fun delegatingPasswordEncoder(): DelegatingPasswordEncoder = DelegatingPasswordEncoder(
        "bcrypt", mapOf("bcrypt" to BCryptPasswordEncoder())
    )
}