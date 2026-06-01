package me.blueblack094.springtest.core.domain.member

import me.blueblack094.springtest.core.domain.base.ExceptionMetadata
import me.blueblack094.springtest.core.global.exception.BaseException
import org.springframework.http.HttpStatus

class MemberNotFoundException : BaseException(
    code = CODE,
    status = HTTP_STATUS,
    message = MESSAGE,
) {
    companion object : ExceptionMetadata {
        override val CODE = "MAX_SIGNUP_AUTH_REQUEST_EXCEEDED"
        override val MESSAGE = "인증 요청 횟수 초과 (10분 대기)"
        override val DESCRIPTION = "$CODE: $MESSAGE"
        override val HTTP_STATUS = HttpStatus.BAD_REQUEST
    }
}