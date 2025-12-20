package com.example.empleos.service;



import com.example.empleos.models.Vacante;
import org.springframework.data.domain.Example;

import java.util.List;

public interface IVacanteService {
    List<Vacante> buscarTodas();
    Vacante buscarPorId(Integer idVacante);
    void guardar(Vacante vacante);
    List<Vacante> buscarDestacadas();
    void eliminar(Integer idVacante);
    List<Vacante> buscarByExample(Example<Vacante> example);
}

