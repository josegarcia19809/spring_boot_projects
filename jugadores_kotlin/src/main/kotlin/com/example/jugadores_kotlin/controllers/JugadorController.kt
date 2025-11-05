package com.example.jugadores_kotlin.controllers

import com.example.jugadores_kotlin.models.Jugador
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class JugadorController {
    private val jugadores = arrayListOf<Jugador>(
        Jugador(
            id = 1,
            nombre = "Kylian Mbappé",
            club = "Real Madrid",
            posicion = "Delantero",
            rating = 92,
            imagen = "mbappe.jpg"
        ),
        Jugador(
            id = 2,
            nombre = "Harry Kane",
            club = "Bayern Munich",
            posicion = "Delantero",
            rating = 91,
            imagen = "kane.jpg"
        ),
        Jugador(
            id = 3,
            nombre = "Erling Haaland",
            club = "Manchester City",
            posicion = "Delantero",
            rating = 93,
            imagen = "haaland.jpg"
        ),
        Jugador(
            id = 4,
            nombre = "Jude Bellingham",
            club = "Real Madrid",
            posicion = "Centrocampista",
            rating = 90,
            imagen = "bellingham.jpg"
        ),
        Jugador(
            id = 5,
            nombre = "Vinicius Júnior",
            club = "Real Madrid",
            posicion = "Extremo Izquierdo",
            rating = 91,
            imagen = "vinicius.jpg"
        ),
        Jugador(
            id = 6,
            nombre = "Lamine Yamal",
            club = "FC Barcelona",
            posicion = "Extremo Derecho",
            rating = 85,
            imagen = "yamal.jpg"
        ),
        Jugador(
            id = 7,
            nombre = "Michelle Agyemang",
            club = "Arsenal",
            posicion = "Delantera",
            rating = 80,
            imagen = "agyemang.jpg"
        )
    )

    // Método para listar a todos los jugadores
    @GetMapping("/jugadores")
    fun listarJugadores(model: Model): String {
        model.addAttribute("jugadores", jugadores)
        return "jugadores_lista"
    }

    // Método para buscar un jugador por id
    fun buscarPorId(id: Int): Jugador? {
        return jugadores.find { it.id == id }
    }

    // Método para ver detalle de un jugador por id
    @GetMapping("/jugadores/{id}")
    fun verDetalle(
        @PathVariable id: Int,
        model: Model
    ): String {
        var jugador = buscarPorId(id)
        return if (jugador != null) {
            model.addAttribute("jugador", jugador)
            "jugador_detalle"
        } else {
            model.addAttribute("mensaje", "El jugador con id $id no existe")
            "error"
        }
    }
}
















