package com.example.asistente_medico.repository;

import com.example.asistente_medico.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository
        extends JpaRepository<Doctor, Long> {
    List<Doctor> findBySpecialtyIgnoreCase(String specialty);

    List<Doctor> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrSpecialtyContainingIgnoreCase(
            String firstName, String lastName, String specialty);
}

