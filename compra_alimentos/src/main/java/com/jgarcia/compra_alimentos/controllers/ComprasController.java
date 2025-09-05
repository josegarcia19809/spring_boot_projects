package com.jgarcia.compra_alimentos.controllers;

import com.jgarcia.compra_alimentos.model.CompraComida;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ComprasController {

    List<CompraComida> listaCompras = new ArrayList<>();

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

    @GetMapping("/obtener-compras")
    public String obtenerCompras(Model model) {
        model.addAttribute("listaCompras", listaCompras);
        return "obtener_compras";
    }

    @GetMapping("/registrar-compra")
    public String registrarCompra(Model model,
                                  @RequestParam(required = false) String id) {
        CompraComida compra;
        int index = obtenerIndexCompra(id);
        if (index == -1) { // Nueva compra
            compra = new CompraComida();
        } else { // Actualizar compra
            compra = listaCompras.get(index);
        }
        model.addAttribute("compra", compra);
        return "form_registrar_compra";
    }

    @PostMapping("/guardarCompra")
    public String guardarCompra(CompraComida compra) {
        int index = obtenerIndexCompra(compra.getId());
        if (index == -1) { // Nueva compra
            listaCompras.add(compra);
        } else { // Actualizar compra
            listaCompras.set(index, compra);
        }
        return "redirect:/obtener-compras";
    }

    private Integer obtenerIndexCompra(String id) {
        for (int i = 0; i < listaCompras.size(); i++) {
            if (listaCompras.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1; // No encontrado
    }
}
