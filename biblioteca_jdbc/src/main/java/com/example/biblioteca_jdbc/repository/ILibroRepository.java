package com.example.biblioteca_jdbc.repository;

import com.example.biblioteca_jdbc.model.Libro;

import java.util.List;
import java.util.Optional;

public interface ILibroRepository {
    List<Libro> findByTitulo(String titulo);
}