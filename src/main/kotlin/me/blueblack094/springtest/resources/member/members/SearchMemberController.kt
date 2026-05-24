package me.blueblack094.springtest.resources.member.members

import me.blueblack094.springtest.core.domain.member.MemberRepo
import me.blueblack094.springtest.core.global.http.CustomResponseEntity
import me.blueblack094.springtest.core.global.pagination.OffsetPage
import me.blueblack094.springtest.resources.member.members.dto.MemberDto
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SearchMemberController(
    private val memberRepo: MemberRepo
) {
    @GetMapping("/members")
    fun search(): CustomResponseEntity<List<MemberDto>> {
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

        return CustomResponseEntity(data = memberDtos)
    }

    @GetMapping("/members2")
    fun search2(
        email: String,
        pageable: Pageable,
    ): CustomResponseEntity<OffsetPage<List<MemberDto>>> {
        val members = memberRepo.findAllByEmail(email, pageable)

        val r = members
            .map(MemberDto::create)
            .let { OffsetPage.create(page = it) }

        return CustomResponseEntity(data = r)
    }
}