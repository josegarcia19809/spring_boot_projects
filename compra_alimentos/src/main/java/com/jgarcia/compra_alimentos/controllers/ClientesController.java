package com.jgarcia.compra_alimentos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientesController {
    @GetMapping(value = "/mostrar-clientes")
    public String mostrarClientes(Model model) {
        return "mostrar_clientes";
    }

    @GetMapping("/")
    public String mostrarIndex(Model model) {
        return "index";
    }
}

