package com.example.videojuegos_kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class VideojuegosKotlinApplication

fun main(args: Array<String>) {
	runApplication<VideojuegosKotlinApplication>(*args)
    println("Ejecutando en puerto 8080...")
}
