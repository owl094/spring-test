package me.blueblack094.springtest.core.global.http.swagger

import me.blueblack094.springtest.core.global.exception.BaseException
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class ApiExceptions(
    vararg val value: KClass<out BaseException>
)