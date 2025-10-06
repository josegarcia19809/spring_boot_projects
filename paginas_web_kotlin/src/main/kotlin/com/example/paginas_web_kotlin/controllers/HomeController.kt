package com.example.paginas_web_kotlin.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {
    @GetMapping("/")
    fun mostrarIndex(): String {
        return "index"
    }

    @GetMapping("/encabezados")
    fun encabezados(): String {
        return "encabezados"
    }

    @GetMapping("/carretera")
    fun carretera(): String {
        return "carretera"
    }
}