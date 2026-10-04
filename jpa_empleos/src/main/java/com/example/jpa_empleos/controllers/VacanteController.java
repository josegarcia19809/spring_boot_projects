package com.example.jpa_empleos.controllers;


import com.example.jpa_empleos.models.Vacante;
import com.example.jpa_empleos.repository.VacantesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jpa-vacante")
public class VacanteController {

    @Autowired
    private VacantesRepository vacantesRepo;

    /**
     * Guardar una vacante
     */
    @PostMapping
    public Vacante guardarVacante(@RequestBody Vacante vacante) {
        return vacantesRepo.save(vacante);
    }

    /**
     * Obtener todas las vacantes
     */
    @GetMapping
    public List<Vacante> buscarVacantes() {
        return vacantesRepo.findAll();
    }
}
