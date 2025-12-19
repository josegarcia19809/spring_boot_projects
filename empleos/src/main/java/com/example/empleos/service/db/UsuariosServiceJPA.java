package com.example.empleos.service.db;

import com.example.empleos.models.Usuario;
import com.example.empleos.repository.UsuarioRepository;
import com.example.empleos.service.IUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariosServiceJPA implements IUsuariosService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void guardar(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public void eliminar(Integer idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }

    @Override
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }
}
