package com.example.cafe_api_rest_db.services;

// CafeServiceImpl.java

import java.util.List;

import com.example.cafe_api_rest_db.repositories.CafeRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cafe_api_rest_db.models.Cafe;

@Service
@Transactional
public class CafeServiceImpl implements CafeService {

    private final CafeRepositorio cafeRepositorio;

    public CafeServiceImpl(CafeRepositorio cafeRepositorio) {
        this.cafeRepositorio = cafeRepositorio;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cafe> findAll() {
        return cafeRepositorio.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Cafe findById(Long id) {
        return cafeRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Café no encontrado con id: "
                        + id));
    }

    @Override
    public Cafe save(Cafe cafe) {
        // aquí podrías añadir lógica adicional (p. ej. normalizar nombre)
        return cafeRepositorio.save(cafe);
    }

    @Override
    public Cafe update(Long id, Cafe cafe) {
        Cafe existente = cafeRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Café no encontrado con id: "
                        + id));

        // Actualiza los campos que desees (evita cambiar id)
        existente.setNombre(cafe.getNombre());
        existente.setTipo(cafe.getTipo());
        existente.setPrecio(cafe.getPrecio());
        existente.setImagen(cafe.getImagen());

        return cafeRepositorio.save(existente);
    }

    @Override
    public void delete(Long id) {
        if (!cafeRepositorio.existsById(id)) {
            throw new RuntimeException("Café no encontrado con id: " + id);
        }
        cafeRepositorio.deleteById(id);
    }
}

