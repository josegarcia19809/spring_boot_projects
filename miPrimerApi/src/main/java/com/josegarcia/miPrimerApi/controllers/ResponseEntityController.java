package com.josegarcia.miPrimerApi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2")
public class ResponseEntityController {

    // ✅ 200 OK: solicitud GET exitosa
    @GetMapping("/respuesta-entity")
    public ResponseEntity<String> metodoGet() {
        return ResponseEntity.ok("✅ Método GET ejecutado correctamente");
    }

    // ✅ Variante con ResponseEntity explícito
    @GetMapping("/respuesta-entity2")
    public ResponseEntity<String> metodoGet2() {
        return new ResponseEntity<>("✅ Método GET v2 ejecutado correctamente",
                HttpStatus.OK);
    }

    // ✅ 200 OK: obtención de recurso por ID
    @GetMapping("/respuesta-entity/{id}")
    public ResponseEntity<String> metodoGetParametroId(@PathVariable("id") String id) {
        return new ResponseEntity<>("📘 Recurso obtenido con id: " + id, HttpStatus.OK);
    }

    // ✅ 200 OK: obtención de recurso por varios parámetros
    @GetMapping("/respuesta-entity/{id}/{nombre}")
    public ResponseEntity<String> metodoGetParametroIdNombre(
            @PathVariable String id,
            @PathVariable String nombre) {
        return new ResponseEntity<>("🥳 Recurso obtenido con id: " + id + " y nombre: " +
                nombre, HttpStatus.OK);
    }

    // 🆕 201 CREATED: se crea un nuevo recurso
    @PostMapping("/respuesta-entity")
    public ResponseEntity<String> metodoPost() {
        return new ResponseEntity<>("🎉 Recurso creado exitosamente (POST)",
                HttpStatus.CREATED);
    }

    // 🔄 200 OK: se actualiza un recurso existente
    @PutMapping("/respuesta-entity")
    public ResponseEntity<String> metodoPut() {
        return new ResponseEntity<>("♻️ Recurso actualizado correctamente (PUT)",
                HttpStatus.OK);
    }

    // 🗑️ 204 NO CONTENT: eliminación exitosa sin cuerpo en la respuesta
    @DeleteMapping("/respuesta-entity")
    public ResponseEntity<String> metodoDelete() {
        return new ResponseEntity<>("🗑️ Recurso eliminado correctamente (DELETE)",
                HttpStatus.NO_CONTENT);
    }
}

