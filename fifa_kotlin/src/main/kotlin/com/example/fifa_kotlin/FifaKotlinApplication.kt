package com.example.fifa_kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FifaKotlinApplication

fun main(args: Array<String>) {
	runApplication<FifaKotlinApplication>(*args)
    println("Ejecutando en puerto 8080...")
}
