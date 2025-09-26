package com.example.hola_mundo_kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication

class HolaMundoKotlinApplication

fun main(args: Array<String>) {
	runApplication<HolaMundoKotlinApplication>(*args)

    println("Ejecutando servidor...")
}

