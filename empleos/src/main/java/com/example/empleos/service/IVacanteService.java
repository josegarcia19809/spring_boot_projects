package com.example.empleos.service;



import com.example.empleos.models.Vacante;

import java.util.List;

public interface IVacanteService {
    List<Vacante> buscarTodas();
    Vacante buscarPorId(Integer idVacante);
}

