package com.example.empleos.service;

import com.example.empleos.models.Solicitud;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISolicitudesService {
    void guardar(Solicitud solicitud);

    void eliminar(Integer idSolicitud);

    Solicitud buscarPorId(Integer idSolicitud);

    List<Solicitud> buscarTodas();

    Page<Solicitud> buscarTodos(Pageable pageable);
}
