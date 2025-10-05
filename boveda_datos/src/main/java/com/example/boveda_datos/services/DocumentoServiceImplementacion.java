package com.example.boveda_datos.services;

import com.example.boveda_datos.models.Documento;
import com.example.boveda_datos.repositories.DocumentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentoServiceImplementacion implements DocumentoServiceI {

    private final DocumentoRepository documentoRepository;

    public DocumentoServiceImplementacion(DocumentoRepository documentoRepository) {
        this.documentoRepository = documentoRepository;
    }

    @Override
    public List<Documento> listarTodos() {
        return documentoRepository.findAll();
    }

    @Override
    public Documento obtenerPorId(Long id) {
        return documentoRepository.findById(id).orElse(null);
    }

    @Override
    public void guardar(Documento documento) {
        documentoRepository.save(documento);
    }

    @Override
    public void eliminar(Long id) {
        documentoRepository.deleteById(id);
    }
}
