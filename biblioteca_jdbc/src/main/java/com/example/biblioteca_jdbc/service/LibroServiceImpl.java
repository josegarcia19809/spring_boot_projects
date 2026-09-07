package com.example.biblioteca_jdbc.service;

import com.example.biblioteca_jdbc.model.Libro;
import com.example.biblioteca_jdbc.repository.ILibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroServiceImpl implements ILibroService {

    private final ILibroRepository libroRepository;

    public LibroServiceImpl(ILibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public List<Libro> buscarPorTitulo(String titulo) {
        return libroRepository.findByTitulo(titulo);
    }
}
