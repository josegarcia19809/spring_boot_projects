package com.example.boveda_datos.services;

import com.example.boveda_datos.models.Proyecto;
import com.example.boveda_datos.repositories.ProyectoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProyectoServiceImplementacion implements ProyectoServiceI {

    private final ProyectoRepository proyectoRepository;

    public ProyectoServiceImplementacion(ProyectoRepository proyectoRepository) {
        this.proyectoRepository = proyectoRepository;
    }

    @Override
    public List<Proyecto> listarTodos() {
        return proyectoRepository.findAll();
    }

    @Override
    public Proyecto obtenerPorId(Long id) {
        return proyectoRepository.findById(id).orElse(null);
    }

    @Override
    public Proyecto guardar(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    @Override
    public void eliminar(Long id) {
        proyectoRepository.deleteById(id);
    }
}

