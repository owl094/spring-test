package me.blueblack094.springtest.core.domain.member

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MemberRepo : JpaRepository<Member, UUID> {
}