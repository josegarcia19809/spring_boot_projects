package com.example.empleos.controllers;


import com.example.empleos.models.Solicitud;
import com.example.empleos.models.Vacante;
import com.example.empleos.service.IVacanteService;
import com.example.empleos.util.Utileria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public String save(Solicitud solicitud, BindingResult result, Model model,
                       @RequestParam("archivoCV") MultipartFile archivoCV) {
        System.out.println("Solicitud " + solicitud);
        if (result.hasErrors()) {
            System.out.println("Errores existieron");
            return "solicitudes/formSolicitud";
        }
        if (!archivoCV.isEmpty()) {
            String nombreArchivo = Utileria.guardarArchivo(archivoCV, rutaCV);
            if (nombreArchivo != null) {
                solicitud.setArchivo(nombreArchivo);
            }
        }
        return "redirect:/";
    }
}
