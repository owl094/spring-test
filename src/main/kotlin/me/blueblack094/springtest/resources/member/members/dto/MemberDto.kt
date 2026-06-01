package me.blueblack094.springtest.resources.member.members.dto

import me.blueblack094.springtest.core.domain.member.Member
import java.util.*

data class MemberDto(
    val id: UUID,
    val name: String,
    val age: Int,
    val email: String,
    val phone: String,
) {
    companion object {
        fun create(member: Member): MemberDto {
            return MemberDto(
                id = member.id,
                name = member.name,
                age = member.age,
                email = member.email,
                phone = member.phone,
            )
        }
    }
}
