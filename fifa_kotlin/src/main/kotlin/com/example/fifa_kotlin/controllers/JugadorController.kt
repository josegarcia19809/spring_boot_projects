package com.example.fifa_kotlin.controllers

import com.example.fifa_kotlin.repositories.JugadorRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class JugadorController(
    private val playerRepository: JugadorRepository
) {
    // Método para listar a todos los jugadores
    @GetMapping("/jugadores")
    fun listarJugadores(model: Model): String {
        model.addAttribute("jugadores", playerRepository.obtenerJugadores())
        model.addAttribute("cantidadJugadores", playerRepository.obtenerCantidadJugadores())
        return "jugadores_lista"
    }

    @GetMapping("/jugadores-ordenados-goles")
    fun ordenarPorGoles(model: Model): String{
        model.addAttribute("jugadores", playerRepository.ordenarJugadoresPorGoles())

        model.addAttribute("cantidadJugadores", playerRepository.obtenerCantidadJugadores())
        return "jugadores-ordenados-goles"
    }

}