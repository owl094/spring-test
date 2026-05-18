package me.blueblack094.springtest.core.global.security

import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .headers {
                //  H2 콘솔 iframe 허용을 위한 설정
                it.frameOptions { frameOptions -> frameOptions.sameOrigin() }
            }
            .sessionManagement { sm ->
                // 세션 사용 안 함 -> JWT로 대체
                sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests { authorize ->
                authorize
                    // H2 콘솔 경로 허용
                    .requestMatchers(PathRequest.toH2Console()).permitAll()
                    .requestMatchers("/").permitAll()
                    // 테스트용 임시 개방
                    .requestMatchers("/members").permitAll()
                    .anyRequest()
                    .authenticated()
            }

        return http.build()
    }
}