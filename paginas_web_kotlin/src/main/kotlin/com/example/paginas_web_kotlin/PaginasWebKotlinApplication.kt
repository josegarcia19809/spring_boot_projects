package com.example.paginas_web_kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PaginasWebKotlinApplication

fun main(args: Array<String>) {
    runApplication<PaginasWebKotlinApplication>(*args)
    println("Ejecutando sitio...")
}
