package com.example.empleos.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/solicitudes")
public class SolicitudesController {
    @GetMapping("/create/{idVacante}")
    public String create(@PathVariable("idVacante") Long idVacante, Model model) {
        System.out.println("IdVacante: " + idVacante);
        return "solicitudes/formSolicitud";
    }
}
