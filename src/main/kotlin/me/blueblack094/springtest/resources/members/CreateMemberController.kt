package me.blueblack094.springtest.resources.members

import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import me.blueblack094.springtest.core.domain.member.Member
import me.blueblack094.springtest.core.domain.member.MemberRepo
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

data class CreateMemberRequest(
    @field:NotBlank
    val name: String,

    @field:Min(0)
    val age: Int,

    @field:Email
    val email: String,

    @field:Pattern(regexp = "^010-\\d{4}-\\d{4}$")
    val phone: String,
)

@RestController
class CreateMemberController(
    private val memberRepo: MemberRepo
) {
    @PostMapping("/members")
    fun create(
        @RequestBody @Valid req: CreateMemberRequest
    ): UUID {
        val member = Member.create(
            name = req.name,
            age = req.age,
            email = req.email,
            phone = req.phone,
        )
        
        memberRepo.save(member)

        return member.id
    }
}