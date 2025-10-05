package com.example.boveda_datos.services;

import com.example.boveda_datos.models.Proyecto;

import java.util.List;

public interface ProyectoServiceI {
    List<Proyecto> listarTodos();

    Proyecto obtenerPorId(Long id);

    Proyecto guardar(Proyecto proyecto);

    void eliminar(Long id);
}

