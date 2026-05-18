package me.blueblack094.springtest.resources.health

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckController {
    @GetMapping("/")
    fun healthCheck(): String {
        return "OK"
    }
}