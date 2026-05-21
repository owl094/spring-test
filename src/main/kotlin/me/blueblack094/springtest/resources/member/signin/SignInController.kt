package me.blueblack094.springtest.resources.member.signin

import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import me.blueblack094.springtest.core.domain.member.MemberNotFoundException
import me.blueblack094.springtest.core.domain.member.MemberRepo
import me.blueblack094.springtest.core.global.http.CustomResponseEntity
import me.blueblack094.springtest.core.global.security.SecurityJwtTokenProvider
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

data class SignInRequest(
    @field:Email
    val email: String,

    @field:NotNull
    val password: String
)

@RestController
class SignInController(
    private val authenticationManager: AuthenticationManager,
    private val jwtTokenProvider: SecurityJwtTokenProvider,
    private val memberRepo: MemberRepo
) {
    @PostMapping("/signin")
    fun signIn(
        @RequestBody @Valid req: SignInRequest
    ): CustomResponseEntity<String> {
        val authentication =
            UsernamePasswordAuthenticationToken(req.email, req.password)
                .let(authenticationManager::authenticate)

        val member = memberRepo
            .findByEmail(req.email)
            ?: run { throw MemberNotFoundException() }

        val jwtToken = jwtTokenProvider.createToken(
            authentication = authentication,
            userId = member.id.toString()
        )

        return CustomResponseEntity(data = jwtToken)
    }
}