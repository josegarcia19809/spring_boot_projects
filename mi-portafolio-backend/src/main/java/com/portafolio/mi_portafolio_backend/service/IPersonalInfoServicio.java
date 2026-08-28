package com.portafolio.mi_portafolio_backend.service;

import com.portafolio.mi_portafolio_backend.model.PersonalInfo;

import java.util.List;
import java.util.Optional;

public interface IPersonalInfoServicio {

    PersonalInfo guardar(PersonalInfo personalInfo);

    Optional<PersonalInfo> buscarPorId(Long id);

    List<PersonalInfo> buscarTodos();

    void eliminarPorId(Long id);
}