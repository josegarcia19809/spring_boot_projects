package com.example.dataVault_v1.services;

import com.example.dataVault_v1.models.Proyecto;
import com.example.dataVault_v1.repositories.ProyectoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProyectoServiceImpl implements IProyectoService {

    private final ProyectoRepository proyectoRepository;

    public ProyectoServiceImpl(ProyectoRepository proyectoRepository) {
        this.proyectoRepository = proyectoRepository;
    }

    @Override
    public Proyecto guardar(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    @Override
    public Optional<Proyecto> buscarPorId(Long id) {
        return proyectoRepository.findById(id);
    }

    @Override
    public List<Proyecto> listarTodos() {
        return proyectoRepository.findAll();
    }

    @Override
    public Optional<Proyecto> editar(Long id, Proyecto proyecto) {
        return proyectoRepository.findById(id)
                .map(p -> {
                    p.setNombre(proyecto.getNombre());
                    p.setEmpresa(proyecto.getEmpresa());
                    p.setDescripcion(proyecto.getDescripcion());
                    return proyectoRepository.save(p);
                });
    }

    @Override
    public void eliminar(Long id) {
        proyectoRepository.deleteById(id);
    }
}


