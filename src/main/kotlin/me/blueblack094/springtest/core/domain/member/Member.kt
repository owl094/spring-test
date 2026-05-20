package me.blueblack094.springtest.core.domain.member

import com.github.f4b6a3.uuid.UuidCreator
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "member")
class Member protected constructor(
    @Id
    val id: UUID = UuidCreator.getTimeOrderedEpoch(),

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var age: Int,

    @Column(nullable = false, unique = true)
    var email: String,

    @Column(nullable = false)
    val password: String,

    @Column(nullable = false, unique = true, length = 13)
    var phone: String
) {
    companion object {
        fun create(
            name: String,
            age: Int,
            email: String,
            password: String,
            phone: String,
        ): Member {
            return Member(
                name = name,
                age = age,
                email = email,
                password = password,
                phone = phone,
            )
        }
    }
}