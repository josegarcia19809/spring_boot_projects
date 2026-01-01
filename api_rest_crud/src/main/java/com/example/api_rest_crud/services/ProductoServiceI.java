package com.example.api_rest_crud.services;

import com.example.api_rest_crud.entities.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoServiceI {
    List<Producto> buscarTodos();

    Optional<Producto> buscarPorId(Long id);

    Producto Guardar(Producto product);

    Optional<Producto> actualizar(Long id, Producto product);

    Optional<Producto> borrar(Long id);
}
