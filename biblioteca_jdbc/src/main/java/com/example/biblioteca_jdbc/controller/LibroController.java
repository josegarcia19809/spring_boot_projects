package com.example.biblioteca_jdbc.controller;

import com.example.biblioteca_jdbc.model.Libro;
import com.example.biblioteca_jdbc.service.ILibroService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final ILibroService libroService;

    public LibroController(ILibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping("/buscar")
    public List<Libro> buscarPorTitulo(
            @RequestParam String titulo) {

        return libroService.buscarPorTitulo(titulo);
    }
}
