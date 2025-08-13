package com.example.hola_mundo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String inicio(){
        return "<h1>Página de inicio</h1>";
    }

    @GetMapping("/saludos")
    public String saludos(){
        return "<h2>Saludos, Atlacomulco</h2>";
    }
}
