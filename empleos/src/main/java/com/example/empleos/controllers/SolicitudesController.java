package com.example.empleos.controllers;


import com.example.empleos.models.Solicitud;
import com.example.empleos.models.Vacante;
import com.example.empleos.service.IVacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/solicitudes")
public class SolicitudesController {

    @Value("${empleosapp.ruta.cv}")
    private String rutaCV;

    @Autowired
    private IVacanteService vacanteService;

    @GetMapping("/create/{idVacante}")
    public String create(Solicitud solicitud, @PathVariable("idVacante") Integer idVacante,
                         Model model) {
        Vacante vacante = vacanteService.buscarPorId(idVacante);
        model.addAttribute("vacante", vacante);
        return "solicitudes/formSolicitud";
    }

    @PostMapping("/save")

    public String save(Solicitud solicitud, Model model) {
        System.out.println("Solicitud "+ solicitud);
        return "redirect:/";
    }
}
