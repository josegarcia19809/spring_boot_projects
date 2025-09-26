package com.example.animales.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AnimalesController {
    @GetMapping("/")
    fun inicio(): String {
        return "<h2>🐻🐭Animales favoritos 🐸🐢</h2>"
    }

    @GetMapping("/oso")
    fun mostrarOso(): String {
        return "<h3>Oso cariñoso 🐻</h3>"
    }

    @GetMapping("/perro")
    fun mostrarPerro(): String {
        return "<h3>Perro Firulais 🐕</h3>"
    }

    @GetMapping("/gato")
    fun mostrarGato(): String {
        return "<h3>Gato Mixi 🐈</h3>"
    }
}