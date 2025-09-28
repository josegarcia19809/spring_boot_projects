package com.example.boveda_datos.services;

import com.example.boveda_datos.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioServiceI {

    List<Usuario> listarUsuarios();

    Optional<Usuario> obtenerUsuarioPorId(Long id);

    Usuario crearUsuario(Usuario usuario);

    Usuario actualizarUsuario(Long id, Usuario datosActualizados);

    void eliminarUsuario(Long id);
}
