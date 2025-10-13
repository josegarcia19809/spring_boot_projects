package com.example.cartelera_kotlin.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class CineController {
    @GetMapping("/")
    fun index(): String {
        return "index"
    }

    @GetMapping("/cartelera")
    fun cartelera(): String {
        return "cartelera"
    }

    @GetMapping("/horarios")
    fun horarios(): String {
        return "horarios"
    }
}