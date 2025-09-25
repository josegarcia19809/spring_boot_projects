package com.example.hola_mundo_kotlin.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {
    @GetMapping("/")
    fun inicio(): String{
        return "<h1>Página de inicio</h1>"
    }
}