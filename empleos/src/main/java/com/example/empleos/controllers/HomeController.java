package com.example.empleos.controllers;

import com.example.empleos.models.Usuario;
import com.example.empleos.service.IUsuariosService;
import com.example.empleos.service.IVacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {
    @Autowired
    IVacanteService vacanteService;

    @Autowired
    private IUsuariosService usuariosService;

    @GetMapping("/signup")
    public String registrarse(Usuario usuario) {
        return "formRegistro";
    }

    @PostMapping("/signup")
    public String guardarRegistro(Usuario usuario,  BindingResult result,
                                  RedirectAttributes attributes) {
        usuariosService.guardar(usuario);
        attributes.addFlashAttribute("msg", "Registro guardado exitosamente");

        return "redirect:/usuarios/index";
    }
    @RequestMapping("/")
    public String index(Model model) {
        return "home";
    }

    @ModelAttribute
    public void setGenericos(Model model) {
        model.addAttribute("vacantes", vacanteService.buscarDestacadas());
    }
}
