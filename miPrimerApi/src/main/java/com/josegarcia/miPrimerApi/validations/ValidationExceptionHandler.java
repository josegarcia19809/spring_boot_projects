package com.josegarcia.miPrimerApi.validations;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        List<Map<String, Object>> errores = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> {
                    Map<String, Object> err = new HashMap<>();
                    err.put("campo", error.getField());
                    err.put("mensaje", error.getDefaultMessage());
                    err.put("valorRechazado", error.getRejectedValue());
                    return err;
                })
                .toList();

        return ResponseEntity.badRequest().body(errores);
    }

    // 2) Body vacío o JSON mal formado
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex) {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("error", "Cuerpo inválido o vacío. Envía JSON válido con " +
                "Content-Type: application/json");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }

    // 3) Manejar ConstraintViolationException (validación a nivel de método)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(
            ConstraintViolationException ex) {

        List<Map<String, Object>> errores = ex.getConstraintViolations()
                .stream()
                .map(violation -> {
                    Map<String, Object> error = new HashMap<>();
                    error.put("propiedad", violation.getPropertyPath().toString());
                    error.put("mensaje", violation.getMessage());
                    error.put("valorRechazado", violation.getInvalidValue());
                    return error;
                })
                .toList();

        return ResponseEntity.badRequest().body(errores);
    }

}

