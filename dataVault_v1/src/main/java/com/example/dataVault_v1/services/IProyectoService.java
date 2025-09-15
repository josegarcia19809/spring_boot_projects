package com.example.dataVault_v1.services;

import com.example.dataVault_v1.models.Proyecto;

import java.util.List;
import java.util.Optional;

public interface IProyectoService {

    Proyecto guardar(Proyecto proyecto);

    Optional<Proyecto> buscarPorId(Long id);

    List<Proyecto> listarTodos();

    Optional<Proyecto> editar(Long id, Proyecto proyecto);

    void eliminar(Long id);
}

