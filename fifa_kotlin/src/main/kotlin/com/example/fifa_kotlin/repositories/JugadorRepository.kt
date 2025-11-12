package com.example.fifa_kotlin.repositories

import com.example.fifa_kotlin.models.Jugador

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import org.springframework.stereotype.Repository
import java.io.File

@Repository
class JugadorRepository {
    // ---- ArrayList global ----
    val jugadores = arrayListOf<Jugador>()

    // 🔹 Bloque init: se ejecuta al crear la instancia
    init {
        leerJugadores()
    }

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

    fun obtenerJugadores(): ArrayList<Jugador>{
        return jugadores
    }

    fun obtenerCantidadJugadores(): Int{
        return jugadores.size
    }

    fun ordenarJugadoresPorGoles(): List<Jugador> {
        return jugadores.sortedByDescending { it.goles }
    }

}