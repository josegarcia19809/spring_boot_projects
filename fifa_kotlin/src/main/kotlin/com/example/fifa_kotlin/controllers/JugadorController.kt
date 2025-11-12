package com.example.fifa_kotlin.controllers

import com.example.fifa_kotlin.repositories.JugadorRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class JugadorController(
    private val jugadorRepository: JugadorRepository
) {
    // Método para listar a todos los jugadores
    @GetMapping("/jugadores")
    fun listarJugadores(model: Model): String {
        model.addAttribute("jugadores", jugadorRepository.obtenerJugadores())
        model.addAttribute("cantidadJugadores", jugadorRepository.obtenerCantidadJugadores())
        return "jugadores_lista"
    }

    @GetMapping("/jugadores-ordenados-goles")
    fun ordenarPorGoles(model: Model): String {
        model.addAttribute("jugadores", jugadorRepository.ordenarJugadoresPorGoles())
        model.addAttribute("cantidadJugadores", jugadorRepository.obtenerCantidadJugadores())
        model.addAttribute("totalGoles", jugadorRepository.obtenerTotalGoles())
        return "jugadores-ordenados-goles"
    }
}