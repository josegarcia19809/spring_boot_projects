package com.josegarcia.miPrimerApi.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class PruebaController {
    @GetMapping("/metodo")
    public String metodoGet() {
        return "Método GET";
    }

    @GetMapping("/metodo/{id}")
    public String metodoGetParametroId(@PathVariable("id") String id) {
        return "Método GET con parámetro id: " + id;
    }

    @GetMapping("/metodo/{id}/{nombre}")
    public String metodoGetParametroIdNombre(@PathVariable("id") String id,
                                             @PathVariable("nombre") String nombre) {
        return "🥳, conectado a Método GET con id: " + id + " " + nombre;
    }

    @PostMapping("/metodo")
    public String metodoPost() {
        return "Método POST";
    }

    @PutMapping("/metodo")
    public String metodoPut() {
        return "Método PUT";
    }

    @DeleteMapping("/metodo")
    public String metodoDelete() {
        return "Método DELETE";
    }

}
