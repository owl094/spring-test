package me.blueblack094.springtest.core.global.exception

import org.springframework.http.HttpStatus

open class BaseException(
    val code: String,
    val status: HttpStatus,
    override val message: String,
) : RuntimeException(message)