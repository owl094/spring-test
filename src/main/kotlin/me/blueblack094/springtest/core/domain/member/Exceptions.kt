package me.blueblack094.springtest.core.domain.member

import org.springframework.http.HttpStatusCode
import org.springframework.web.client.HttpClientErrorException

class MemberNotFoundException : HttpClientErrorException(
    HttpStatusCode.valueOf(400),
    "Member not found"
)