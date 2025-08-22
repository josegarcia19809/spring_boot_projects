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
}
