package com.example.empleos.controllers;

import com.example.empleos.models.Perfil;
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

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
        try {
        if (result.hasErrors()) {
            return "usuarios/formRegistro";
        }
        usuario.setEstatus(1); // Activado por defecto
        usuario.setFechaRegistro(LocalDate.now()); // Fecha de Registro, la fecha actual del servidor

        // Creamos el Perfil que le asignaremos al usuario nuevo
        // Crear el conjunto de perfiles y asignarlo
        Set<Perfil> perfilesUsuario = new HashSet<>();
        Perfil perfil = new Perfil();
        perfil.setId(3); // Perfil USUARIO
        perfilesUsuario.add(perfil);
        usuario.setPerfiles(perfilesUsuario);

        /**
         * Guardamos el usuario en la base de datos. El Perfil se guarda automaticamente
         */
        usuariosService.guardar(usuario);
        attributes.addFlashAttribute("msg", "Registro guardado exitosamente");
        }catch(Exception ex) {
            attributes.addFlashAttribute("msg", "Ocurrio un error durante la operación. " + ex.getMessage());
        }
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
