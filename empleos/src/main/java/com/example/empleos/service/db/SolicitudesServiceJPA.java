package com.example.empleos.service.db;

import com.example.empleos.models.Solicitud;
import com.example.empleos.repository.SolicitudesRepository;
import com.example.empleos.service.ISolicitudesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitudesServiceJPA implements ISolicitudesService {

    @Autowired
    private SolicitudesRepository solicitudesRepository;

    @Override
    public void guardar(Solicitud solicitud) {
        solicitudesRepository.save(solicitud);
    }

    @Override
    public void eliminar(Integer idSolicitud) {
        solicitudesRepository.deleteById(idSolicitud);
    }

    @Override
    public Solicitud buscarPorId(Integer idSolicitud) {
        Optional<Solicitud> solicitud = solicitudesRepository.findById(idSolicitud);
        return solicitud.orElse(null);
    }

    @Override
    public List<Solicitud> buscarTodas() {
        return solicitudesRepository.findAll();
    }

    @Override
    public Page<Solicitud> buscarTodos(Pageable pageable) {
        return solicitudesRepository.findAll(pageable);
    }
}
