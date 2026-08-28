package com.portafolio.mi_portafolio_backend.rest_controller;

import com.portafolio.mi_portafolio_backend.model.PersonalInfo;
import com.portafolio.mi_portafolio_backend.service.IPersonalInfoServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personal-info")
public class PersonalInfoController {

    private final IPersonalInfoServicio personalInfoService;

    public PersonalInfoController(IPersonalInfoServicio personalInfoService) {
        this.personalInfoService = personalInfoService;
    }

    @GetMapping("/all")
    public List<PersonalInfo> getAllPersonalInfo() {
        return personalInfoService.buscarTodos();
    }

    @GetMapping("/{id}")
    public Optional<PersonalInfo> getPersonalInfoById(@PathVariable Long id) {
        Optional<PersonalInfo> personalInfo =
                personalInfoService.buscarPorId(id);

        if (personalInfo.isPresent()) {
            return personalInfo;
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Personal Info no disponible del id: " + id
            );
        }
    }

    @PostMapping
    public ResponseEntity<PersonalInfo> guardar(
            @RequestBody PersonalInfo personalInfo) {

        PersonalInfo personalInfoGuardada =
                personalInfoService.guardar(personalInfo);

        return new ResponseEntity<>(
                personalInfoGuardada,
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonalInfo> editar(
            @PathVariable Long id,
            @RequestBody PersonalInfo personalInfo) {

        Optional<PersonalInfo> informacionExistente =
                personalInfoService.buscarPorId(id);

        if (informacionExistente.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Personal Info no disponible del id: " + id
            );
        }

        personalInfo.setId(id);

        PersonalInfo informacionActualizada =
                personalInfoService.guardar(personalInfo);

        return ResponseEntity.ok(informacionActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {

        Optional<PersonalInfo> informacionExistente =
                personalInfoService.buscarPorId(id);

        if (informacionExistente.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Personal Info no disponible del id: " + id
            );
        }

        personalInfoService.eliminarPorId(id);

        return ResponseEntity.noContent().build();
    }
}