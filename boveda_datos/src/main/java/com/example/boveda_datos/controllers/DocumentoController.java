package com.example.boveda_datos.controllers;

import com.example.boveda_datos.models.Documento;
import com.example.boveda_datos.repositories.ProyectoRepository;
import com.example.boveda_datos.repositories.UsuarioRepository;
import com.example.boveda_datos.services.DocumentoServiceI;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.*;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.FileNotFoundException;

@Controller
@RequestMapping("/documentos")
public class DocumentoController {

    private final DocumentoServiceI documentoService;
    private final ProyectoRepository proyectoRepository;
    private final UsuarioRepository usuarioRepository;

    public DocumentoController(DocumentoServiceI documentoService,
                               ProyectoRepository proyectoRepository,
                               UsuarioRepository usuarioRepository) {
        this.documentoService = documentoService;
        this.proyectoRepository = proyectoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("documentos", documentoService.listarTodos());
        return "documentos/listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("documento", new Documento());
        model.addAttribute("proyectos", proyectoRepository.findAll());
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "documentos/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Documento documento,
                          BindingResult result,
                          @RequestParam("archivoSubido") MultipartFile archivoSubido,
                          Model model) {

        System.out.println("Archivo recibido: " + archivoSubido.getOriginalFilename());
        System.out.println("Tamaño del archivo: " + archivoSubido.getSize());

        // Primero, validamos si se subió el archivo
        if (archivoSubido.isEmpty()) {
            model.addAttribute("errorArchivo", "Debes seleccionar un archivo 1 para subir");
            model.addAttribute("proyectos", proyectoRepository.findAll());
            model.addAttribute("usuarios", usuarioRepository.findAll());
            return "documentos/formulario";
        }


        if (result.hasErrors()) {
            result.getFieldErrors().forEach(error -> {
                System.out.println("Campo: " + error.getField());
                System.out.println("Mensaje: " + error.getDefaultMessage());
            });
            model.addAttribute("errorArchivo", "Debes seleccionar un archivo 2 para subir");
            model.addAttribute("proyectos", proyectoRepository.findAll());
            model.addAttribute("usuarios", usuarioRepository.findAll());
            return "documentos/formulario";
        }

        if (!archivoSubido.isEmpty()) {
            try {
                String nombreArchivo = archivoSubido.getOriginalFilename();

                documento.setArchivo(nombreArchivo);
                // Ruta absoluta al directorio "uploads" (en la raíz del proyecto)
                String uploadDir = new File("uploads").getAbsolutePath();
                Path ruta = Paths.get(uploadDir).resolve(nombreArchivo);

                Files.createDirectories(ruta.getParent()); // Crea la carpeta si no existe

                archivoSubido.transferTo(ruta.toFile());

            } catch (Exception e) {
                model.addAttribute("errorArchivo", "Error al subir archivo: " +
                        e.getMessage());
                return "documentos/formulario";
            }
        }

        documentoService.guardar(documento);
        return "redirect:/documentos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Documento doc = documentoService.obtenerPorId(id);
        if (doc == null) return "redirect:/documentos";
        model.addAttribute("documento", doc);
        model.addAttribute("proyectos", proyectoRepository.findAll());
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "documentos/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        documentoService.eliminar(id);
        return "redirect:/documentos";
    }

    @GetMapping("/descargar/{nombre}")
    public ResponseEntity<Resource> descargar(@PathVariable String nombre)
            throws IOException {
        Path ruta = Paths.get("uploads").resolve(nombre).toAbsolutePath();
        Resource recurso = new UrlResource(ruta.toUri());

        if (!recurso.exists() || !recurso.isReadable()) {
            throw new FileNotFoundException("Archivo no encontrado: " + nombre);
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
                        recurso.getFilename() + "\"")
                .body(recurso);
    }
}

