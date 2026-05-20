package me.blueblack094.springtest.core.global.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class SecurityJwtAuthenticationFilter(
    private val jwtTokenProvider: SecurityJwtTokenProvider
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        resolveToken(request)
            ?.takeIf(jwtTokenProvider::validateToken)
            ?.let(jwtTokenProvider::getAuthentication)
            ?.let { authentication ->
                SecurityContextHolder.getContext().authentication = authentication
            }

        filterChain.doFilter(request, response)
    }

    private fun resolveToken(request: HttpServletRequest): String? {
        return request
            .getHeader("Authorization")
            .let { it?.substringAfter("Bearer ") }
    }
}