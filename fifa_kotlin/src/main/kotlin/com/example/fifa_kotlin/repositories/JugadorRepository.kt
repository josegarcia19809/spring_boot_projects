package com.example.fifa_kotlin.repositories

import com.example.fifa_kotlin.models.Jugador

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File

class JugadorRepository {
    // ---- ArrayList global ----
    val jugadores = arrayListOf<Jugador>()

    // ---- Función para leer el CSV y llenar la lista ----
    fun leerJugadores() {
        jugadores.clear() // limpiar lista

        csvReader().open(File("jugadores.csv")) {
            readAllWithHeaderAsSequence().forEach { row ->
                jugadores.add(
                    Jugador(
                        numero = row.getValue("numero").toInt(),
                        nombre = row.getValue("nombre"),
                        posicion = row.getValue("posicion"),
                        goles = row.getValue("goles").toInt(),
                    )
                )
            }
        }
        println("Se cargaron ${jugadores.size} jugadores desde el CSV ✅")
    }

}