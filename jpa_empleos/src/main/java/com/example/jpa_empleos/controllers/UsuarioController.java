package com.example.jpa_empleos.controllers;

import com.example.jpa_empleos.models.Perfil;
import com.example.jpa_empleos.models.Usuario;
import com.example.jpa_empleos.repository.PerfilesRepository;
import com.example.jpa_empleos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jpa-usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private PerfilesRepository perfilesRepo;


    /**
     * Buscar un usuario por ID y mostrar sus perfiles
     */
    @GetMapping("/{id}")
    public Usuario buscarUsuario(@PathVariable Integer id) {

        Optional<Usuario> usuarioOptional = usuarioRepo.findById(id);

        if (usuarioOptional.isPresent()) {
            return usuarioOptional.get();
        }

        return null;
    }


    /**
     * Crear un usuario
     */
    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {

        return usuarioRepo.save(usuario);
    }


    /**
     * Crear los perfiles
     */
    @PostMapping("/perfiles")
    public List<Perfil> crearPerfiles(
            @RequestBody List<Perfil> perfiles) {

        return perfilesRepo.saveAll(perfiles);
    }
}