package com.example.empleos.controllers;

import com.example.empleos.service.IUsuariosService;
import com.example.empleos.service.db.UsuariosServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    IUsuariosService usuariosService;

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        model.addAttribute("usuarios", usuariosService.buscarTodos());
        return "usuarios/listUsuarios";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idUsuario,
                           RedirectAttributes attributes) {
        usuariosService.eliminar(idUsuario);
        attributes.addFlashAttribute("message", "Usuario eliminado exitosamente");
        return "redirect:/usuarios/index";
    }
}
