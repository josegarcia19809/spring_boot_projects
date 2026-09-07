package com.example.biblioteca_jdbc.service;

import com.example.biblioteca_jdbc.model.Libro;

import java.util.List;
import java.util.Optional;

public interface ILibroService {
    List<Libro> buscarPorTitulo(String titulo);
}