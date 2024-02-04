package com.shamsheev.wildberries.api.statistics.adapter

import com.shamsheev.wildberries.api.statistics.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.stream.Collectors

class UserDetailsAdapter(private val user: User) : UserDetails {
    private val authoritiesList: MutableCollection<out GrantedAuthority>

    init {
        authoritiesList =
            user.groups.split(",").stream()
                .map(::SimpleGrantedAuthority)
                .collect(Collectors.toList())
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = authoritiesList
    override fun getPassword(): String = user.password
    override fun getUsername(): String = user.username
    override fun isAccountNonExpired(): Boolean = !user.expired
    override fun isAccountNonLocked(): Boolean = !user.locked
    override fun isCredentialsNonExpired(): Boolean = !user.credExpired
    override fun isEnabled(): Boolean = user.enabled
}