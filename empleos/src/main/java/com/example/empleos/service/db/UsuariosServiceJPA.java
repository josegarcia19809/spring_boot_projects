package com.example.empleos.service.db;

import com.example.empleos.models.Usuario;
import com.example.empleos.service.IUsuariosService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariosServiceJPA implements IUsuariosService {
    @Override
    public void guardar(Usuario usuario) {

    }

    @Override
    public void eliminar(Integer idUsuario) {

    }

    @Override
    public List<Usuario> buscarTodos() {
        return List.of();
    }
}
