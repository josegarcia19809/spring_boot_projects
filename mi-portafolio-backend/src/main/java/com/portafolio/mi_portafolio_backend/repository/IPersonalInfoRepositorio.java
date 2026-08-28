package com.portafolio.mi_portafolio_backend.repository;
import com.portafolio.mi_portafolio_backend.model.PersonalInfo;

import java.util.List;
import java.util.Optional;


public interface IPersonalInfoRepositorio {

    PersonalInfo guardar(PersonalInfo informacionPersonal);

    Optional<PersonalInfo> buscarPorId(Long id);

    List<PersonalInfo> buscarTodos();

    void eliminarPorId(Long id);
}