package com.example.empleos.controllers;

import com.example.empleos.models.Vacante;
import com.example.empleos.service.IVacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    IVacanteService vacanteService;
    @RequestMapping("/")
    public String index(Model model) {

        List<Vacante> vacantes = vacanteService.buscarTodas();
        model.addAttribute("vacantes", vacantes);
        return "home";
    }
}
