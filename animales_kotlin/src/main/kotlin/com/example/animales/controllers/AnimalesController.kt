package com.example.animales.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AnimalesController {
    @GetMapping("/")
    fun inicio(): String{

        return "<h2>🐻🐭Animales favoritos 🐸🐢</h2>"
    }

    @GetMapping("/oso")
    fun mostrarOso(): String{
        return "<h3>Oso cariñoso 🐻</h3>"
    }
}