package com.example.empleos.controllers;

import com.example.empleos.models.Perfil;
import com.example.empleos.models.Usuario;
import com.example.empleos.models.Vacante;
import com.example.empleos.service.ICategoriasService;
import com.example.empleos.service.IUsuariosService;
import com.example.empleos.service.IVacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class HomeController {
    @Autowired
    IVacanteService vacanteService;

    @Autowired
    private IUsuariosService usuariosService;

    @Autowired
    private ICategoriasService categoriasService;

    @GetMapping("/signup")
    public String registrarse(Usuario usuario) {
        return "formRegistro";
    }

    @PostMapping("/signup")
    public String guardarRegistro(Usuario usuario, BindingResult result,
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
        } catch (Exception ex) {
            attributes.addFlashAttribute("msg", "Ocurrio un error durante la operación. " +
                    ex.getMessage());
        }
        return "redirect:/usuarios/index";
    }

    @RequestMapping("/")
    public String index(Model model) {
        return "home";
    }

    @GetMapping("/index")
    public String index(Authentication authentication, Model model) {
        String userName = authentication.getName();
        for(GrantedAuthority rol : authentication.getAuthorities()) {
            System.out.println(rol.getAuthority());
        }
        System.out.println("userName: " + userName);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String buscar(@ModelAttribute("search") Vacante vacante, Model model) {
        System.out.println("Buscando vacante " + vacante);
        // La búsqueda ya no será exacta, será como un like
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("descripcion",
                        ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Vacante> example = Example.of(vacante, matcher);
        List<Vacante> lista = vacanteService.buscarByExample(example);
        model.addAttribute("vacantes", lista);
        return "home";
    }

    /**
     * InitBinder para Strings, si los detecta vacíos en el DataBinding los establece
     * en NULL
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @ModelAttribute
    public void setGenericos(Model model) {
        model.addAttribute("vacantes", vacanteService.buscarDestacadas());
        model.addAttribute("categorias", categoriasService.buscarTodas());

        Vacante vacante = new Vacante();
        vacante.reset();
        model.addAttribute("search", vacante);
    }
}
