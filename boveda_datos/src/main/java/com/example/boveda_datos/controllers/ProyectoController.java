package com.example.boveda_datos.controllers;

import com.example.boveda_datos.models.Proyecto;
import com.example.boveda_datos.services.ProyectoServiceI;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/proyectos")
public class ProyectoController {

    private final ProyectoServiceI proyectoService;

    public ProyectoController(ProyectoServiceI proyectoService) {
        this.proyectoService = proyectoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("proyectos", proyectoService.listarTodos());
        return "proyectos/listar";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("proyecto", new Proyecto());
        return "proyectos/formulario";
    }

    @PostMapping
    public String guardar(@Valid @ModelAttribute Proyecto proyecto, BindingResult result,
                          Model model) {
        if (result.hasErrors()) {
            model.addAttribute("proyecto", proyecto);
            return "proyectos/formulario";
        }
        proyectoService.guardar(proyecto);
        return "redirect:/proyectos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Proyecto proyecto = proyectoService.obtenerPorId(id);
        if (proyecto != null) {
            model.addAttribute("proyecto", proyecto);
            return "proyectos/formulario";
        }
        return "redirect:/proyectos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        proyectoService.eliminar(id);
        return "redirect:/proyectos";
    }
}

