package com.example.dataVault_v1.controllers;


import com.example.dataVault_v1.models.Proyecto;
import com.example.dataVault_v1.services.IProyectoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/proyectos")
public class ProyectoWebController {

    private final IProyectoService proyectoService;

    public ProyectoWebController(IProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    // LISTAR
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("proyectos", proyectoService.listarTodos());
        return "lista-proyectos"; // vista: templates/lista-proyectos.html
    }

    // MOSTRAR FORM NUEVO
    @GetMapping("/nuevo")
    public String mostrarFormNuevo(Model model) {
        model.addAttribute("proyecto", new Proyecto());
        return "formulario-proyecto"; // vista: templates/formulario-proyecto.html
    }

    // GUARDAR (CREAR)
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("proyecto") Proyecto proyecto,
                          BindingResult result,
                          RedirectAttributes flash) {
        if (result.hasErrors()) {
            // Vuelve al formulario mostrando errores de validación
            return "formulario-proyecto";
        }
        proyectoService.guardar(proyecto);
        flash.addFlashAttribute("exito", "Proyecto guardado correctamente");
        return "redirect:/proyectos";
    }

    // MOSTRAR FORM EDICIÓN
    @GetMapping("/editar/{id}")
    public String mostrarFormEditar(@PathVariable Long id, Model model, RedirectAttributes flash) {
        Optional<Proyecto> opt = proyectoService.buscarPorId(id);
        if (opt.isEmpty()) {
            flash.addFlashAttribute("error", "El proyecto con id " + id + " no existe");
            return "redirect:/proyectos";
        }
        model.addAttribute("proyecto", opt.get());
        return "formulario-proyecto"; // reutilizamos el mismo formulario
    }

    // ACTUALIZAR (EDITAR)
    @PostMapping("/editar/{id}")
    public String editar(@PathVariable Long id,
                         @Valid @ModelAttribute("proyecto") Proyecto proyecto,
                         BindingResult result,
                         RedirectAttributes flash) {
        if (result.hasErrors()) {
            // Mantén el usuario en el formulario con los mensajes de error
            return "formulario-proyecto";
        }

        // Forzar el id del path para prevenir manipulación del formulario
        proyecto.setId(id);

        // Opción con Optional en el service:
        return proyectoService.editar(id, proyecto)
                .map(pActualizado -> {
                    flash.addFlashAttribute("exito", "Proyecto actualizado");
                    return "redirect:/proyectos";
                })
                .orElseGet(() -> {
                    result.reject("notFound", "El proyecto que intentas editar no existe");
                    return "formulario-proyecto";
                });
    }

    // ELIMINAR
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
        Optional<Proyecto> opt = proyectoService.buscarPorId(id);
        if (opt.isEmpty()) {
            flash.addFlashAttribute("error", "El proyecto con id " + id + " no existe");
            return "redirect:/proyectos";
        }
        proyectoService.eliminar(id);
        flash.addFlashAttribute("exito", "Proyecto eliminado");
        return "redirect:/proyectos";
    }

}

