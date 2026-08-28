package com.portafolio.mi_portafolio_backend.service;

import com.portafolio.mi_portafolio_backend.model.PersonalInfo;
import com.portafolio.mi_portafolio_backend.repository.IPersonalInfoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalInfoServicioImpl implements IPersonalInfoServicio {

    private final IPersonalInfoRepositorio informacionPersonalRepositorio;

    public PersonalInfoServicioImpl(
            IPersonalInfoRepositorio informacionPersonalRepositorio) {
        this.informacionPersonalRepositorio = informacionPersonalRepositorio;
    }

    @Override
    public PersonalInfo guardar(PersonalInfo personalInfo) {
        return informacionPersonalRepositorio.guardar(personalInfo);
    }

    @Override
    public Optional<PersonalInfo> buscarPorId(Long id) {
        return informacionPersonalRepositorio.buscarPorId(id);
    }

    @Override
    public List<PersonalInfo> buscarTodos() {
        return informacionPersonalRepositorio.buscarTodos();
    }

    @Override
    public void eliminarPorId(Long id) {
        informacionPersonalRepositorio.eliminarPorId(id);
    }
}