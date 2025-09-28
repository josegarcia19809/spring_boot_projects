package com.example.boveda_datos.controllers;

import com.example.boveda_datos.models.TipoUsuario;
import com.example.boveda_datos.models.Usuario;
import com.example.boveda_datos.services.UsuarioServiceI;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioServiceI usuarioService;

    public UsuarioController(UsuarioServiceI usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuarios/lista"; // Thymeleaf buscará templates/usuarios/lista.html
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("tipos", TipoUsuario.values());
        return "usuarios/formulario";
    }

    @PostMapping
    public String guardarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tipos", TipoUsuario.values());
            return "usuarios/formulario";
        }
        usuarioService.crearUsuario(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        model.addAttribute("usuario", usuario);
        model.addAttribute("tipos", TipoUsuario.values());
        return "usuarios/formulario";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarUsuario(@PathVariable Long id,
                                    @Valid @ModelAttribute("usuario") Usuario usuario,
                                    BindingResult result,
                                    Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tipos", TipoUsuario.values());
            return "usuarios/formulario";
        }
        usuarioService.actualizarUsuario(id, usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return "redirect:/usuarios";
    }
}

