package me.blueblack094.springtest.resources.member.members.dto

import java.util.*

data class MemberDto(
    val id: UUID,
    val name: String,
    val age: Int,
    val email: String,
    val phone: String,
)
