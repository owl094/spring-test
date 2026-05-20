package me.blueblack094.springtest.core.global.security

import me.blueblack094.springtest.core.domain.member.MemberNotFoundException
import me.blueblack094.springtest.core.domain.member.MemberRepo
import me.blueblack094.springtest.core.domain.member.UserRole
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val memberRepo: MemberRepo
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val member = memberRepo.findByEmail(username)
            ?: throw MemberNotFoundException()

        val roles = listOf(object : GrantedAuthority {
            override fun getAuthority(): String {
                return UserRole.MEMBER.name
            }
        })

        return User(
            member.email,
            member.password,
            roles
        )
    }

}