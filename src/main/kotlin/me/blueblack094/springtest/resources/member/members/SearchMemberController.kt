package me.blueblack094.springtest.resources.member.members

import me.blueblack094.springtest.core.domain.member.MemberRepo
import me.blueblack094.springtest.resources.member.members.dto.MemberDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SearchMemberController(
    private val memberRepo: MemberRepo
) {
    @GetMapping("/members")
    fun search(): List<MemberDto> {
        val members = memberRepo.findAll()

        val memberDtos = members.map {
            MemberDto(
                id = it.id,
                name = it.name,
                age = it.age,
                email = it.email,
                phone = it.phone,
            )
        }

        return memberDtos
    }
}