package com.example.boveda_datos.services;

import com.example.boveda_datos.models.Documento;

import java.util.List;

public interface DocumentoServiceI {
    List<Documento> listarTodos();

    Documento obtenerPorId(Long id);

    void guardar(Documento documento);

    void eliminar(Long id);
}

