package com.shamsheev.wildberries.api.statistics.model

import com.shamsheev.wildberries.api.statistics.adapter.UserDetailsAdapter
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long? = null,

    @Column(nullable = false, unique = true)
    val username: String,

    var password: String,
    var groups: String,
    var expired: Boolean,
    var locked: Boolean,
    var credExpired: Boolean,
    var enabled: Boolean,
) {
    fun toUserDetails(): UserDetails = UserDetailsAdapter(this)
}
