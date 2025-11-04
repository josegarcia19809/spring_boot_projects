package com.example.restaurante_la_buena_olla

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RestauranteLaBuenaOllaApplication

fun main(args: Array<String>) {
	runApplication<RestauranteLaBuenaOllaApplication>(*args)
    println("Ejecutando Restaurante La buena olla...")
}
