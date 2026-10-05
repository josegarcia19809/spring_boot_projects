package com.example.jpa_empleos.controllers;


import com.example.jpa_empleos.models.EstatusVacante;
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


    /**
     * Buscar vacantes por varios estatus
     * Ejemplo:
     * /api/jpa-vacante/estatus-varios?estatus=Eliminada,Creada
     */
    @GetMapping("/estatus-varios")
    public List<Vacante> buscarVacantesVariosEstatus(
            @RequestParam List<EstatusVacante> estatus) {

        EstatusVacante[] listaEstatus =
                estatus.toArray(new EstatusVacante[0]);

        return vacantesRepo.findByEstatusIn(listaEstatus);
    }


    /**
     * Buscar vacantes por rango de salario
     */
    @GetMapping("/salario")
    public List<Vacante> buscarVacantesSalario(
            @RequestParam Double minimo,
            @RequestParam Double maximo) {

        return vacantesRepo.findBySalarioBetween(minimo, maximo);
    }


    /**
     * Buscar vacantes por rango de salario
     * ordenadas de mayor a menor salario
     */
    @GetMapping("/salario/desc")
    public List<Vacante> buscarVacantesSalarioDesc(
            @RequestParam Double minimo,
            @RequestParam Double maximo) {

        return vacantesRepo.findBySalarioBetweenOrderBySalarioDesc(
                minimo, maximo);
    }


    /**
     * Buscar vacantes destacadas por estatus
     * ordenadas por ID descendente
     */
    @GetMapping("/destacadas")
    public List<Vacante> buscarVacantesPorDestacadoEstatus(
            @RequestParam Integer destacado,
            @RequestParam EstatusVacante estatus) {

        return vacantesRepo.findByDestacadoAndEstatusOrderByIdDesc(
                destacado,
                estatus);
    }


    /**
     * Buscar vacantes por estatus
     */
    @GetMapping("/estatus")
    public List<Vacante> buscarVacantesPorEstatus(
            @RequestParam EstatusVacante estatus) {

        return vacantesRepo.findByEstatus(estatus);
    }
}
