package me.blueblack094.springtest.core.global.exception.handlers

import me.blueblack094.springtest.core.global.exception.BaseException
import me.blueblack094.springtest.core.global.http.CustomResponseEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalBaseExceptionHandler {
    /**
     * BaseException 처리
     */
    @ExceptionHandler(BaseException::class)
    fun handle(e: BaseException): ResponseEntity<CustomResponseEntity<Nothing>> {
        return ResponseEntity
            .status(e.status)
            .body(
                CustomResponseEntity(
                    code = e.code,
                    message = e.message,
                    data = null
                )
            )
    }
}