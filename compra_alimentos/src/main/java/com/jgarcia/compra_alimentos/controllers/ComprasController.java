package com.jgarcia.compra_alimentos.controllers;

import com.jgarcia.compra_alimentos.model.CompraComida;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ComprasController {
    @GetMapping("/detalle")
    public String mostrarDetalle(Model model) {
        CompraComida compra = new CompraComida("Wanda", "Female", "Stamford", "Weekly",
                "Burger", 5.66);
        model.addAttribute("compra", compra);
        return "detalle";
    }

    @GetMapping("/genero")
    public String mostrarGenero(Model model) {
        CompraComida compra = new CompraComida("Rachel", "Female", "Philadelphia",
                "Seldom", "Sushi", 71.68);
        model.addAttribute("compra", compra);
        return "genero";
    }

    @GetMapping("/frecuencia")
    public String mostrarFrecuencia(Model model) {
        CompraComida compra = new CompraComida("Sharon", "Female", "New York", "Daily",
                "Donut", 25.04);
        model.addAttribute("compra", compra);
        return "frecuencia";
    }


}
