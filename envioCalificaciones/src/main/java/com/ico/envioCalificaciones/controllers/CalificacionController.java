package com.ico.envioCalificaciones.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class CalificacionController {
    ArrayList<Calificacion> listaCalificaciones = new ArrayList<>();

    public CalificacionController() {
        listaCalificaciones.add(new Calificacion("Juan Pérez", "Matemáticas", 87.5));
        listaCalificaciones.add(new Calificacion("Ana López", "Historia", 92.0));
        listaCalificaciones.add(new Calificacion("Carlos Ruiz", "Ciencias", 78.3));
        listaCalificaciones.add(new Calificacion("Laura García", "Inglés", 85.7));
        listaCalificaciones.add(new Calificacion("José Gómez", "Física", 91.2));
        listaCalificaciones.add(new Calificacion("Marta Díaz", "Química", 88.6));
    }

    @GetMapping("/lista_calificaciones")
    public String obtenerListaCalificaciones(Model model) {
        model.addAttribute("listaCalificaciones", listaCalificaciones);
        return "lista_calificaciones";
    }

    @GetMapping("/calificacion")
    public String obtenerCalificacion(Model model) {
        // Crear una instancia de Calificacion
        Calificacion calificacion = new Calificacion("José García", "Programación I", 8.9);
        model.addAttribute("calificacion", calificacion);
        return "calificacion";
    }
}
