@file:Suppress("PropertyName")

package me.blueblack094.springtest.core.domain.base

import org.springframework.http.HttpStatus

interface ExceptionMetadata {
    val CODE: String
    val MESSAGE: String
    val DESCRIPTION: String
    val HTTP_STATUS: HttpStatus
}