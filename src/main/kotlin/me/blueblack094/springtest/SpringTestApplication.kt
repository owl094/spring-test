package me.blueblack094.springtest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class SpringTestApplication

fun main(args: Array<String>) {
    runApplication<SpringTestApplication>(*args)
}
