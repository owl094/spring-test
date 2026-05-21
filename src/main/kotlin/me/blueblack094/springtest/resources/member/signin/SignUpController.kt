package me.blueblack094.springtest.resources.member.signin

import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import me.blueblack094.springtest.core.domain.member.Member
import me.blueblack094.springtest.core.domain.member.MemberRepo
import me.blueblack094.springtest.core.global.http.CustomResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

data class SignUpRequest(
    @field:NotBlank
    val name: String,

    @field:Min(0)
    val age: Int,

    @field:Email
    val email: String,

    @field:NotBlank
    val password: String,

    @field:Pattern(regexp = "^010-\\d{4}-\\d{4}$")
    val phone: String,
)

@RestController
class SignUpController(
    private val memberRepo: MemberRepo,
    private val passwordEncoder: PasswordEncoder
) {
    @PostMapping("/signup")
    fun signup(
        @RequestBody @Valid req: SignUpRequest
    ): CustomResponseEntity<String> {
        val encodedPassword = passwordEncoder.encode(req.password)!!

        val member = Member.create(
            name = req.name,
            age = req.age,
            email = req.email,
            password = encodedPassword,
            phone = req.phone,
        )

        memberRepo.save(member)

        return CustomResponseEntity(data = member.id.toString())
    }
}