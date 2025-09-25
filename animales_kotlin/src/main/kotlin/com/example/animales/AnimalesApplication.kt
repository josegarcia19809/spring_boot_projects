package com.example.animales

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AnimalesApplication

fun main(args: Array<String>) {
	runApplication<AnimalesApplication>(*args)
    println("Servidor en ejecución...")
}
