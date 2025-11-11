package com.example.fifa_kotlin

import com.example.fifa_kotlin.repositories.JugadorRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FifaKotlinApplication

fun main(args: Array<String>) {
	runApplication<FifaKotlinApplication>(*args)
    println("Ejecutando en puerto 8080...")
    val jugadores= JugadorRepository()
    jugadores.leerJugadores()

    //jugadores.forEach { println(it.usuarioId) }
}
