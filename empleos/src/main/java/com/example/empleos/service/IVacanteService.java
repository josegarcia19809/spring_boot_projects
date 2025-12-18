package com.example.empleos.service;



import com.example.empleos.models.Vacante;

import java.util.List;

public interface IVacanteService {
    List<Vacante> buscarTodas();
    Vacante buscarPorId(Integer idVacante);
    void guardar(Vacante vacante);
    List<Vacante> buscarDestacadas();
}

