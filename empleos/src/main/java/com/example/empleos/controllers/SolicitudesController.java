package com.example.empleos.controllers;


import com.example.empleos.models.Vacante;
import com.example.empleos.service.IVacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/solicitudes")
public class SolicitudesController {

    @Autowired
    private IVacanteService vacanteService;

    @GetMapping("/create/{idVacante}")
    public String create(@PathVariable("idVacante") Integer idVacante, Model model) {
        Vacante vacante = vacanteService.buscarPorId(idVacante);
        model.addAttribute("vacante", vacante);
        return "solicitudes/formSolicitud";
    }
}
