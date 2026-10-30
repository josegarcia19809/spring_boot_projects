package com.example.asistente_medico.repository;

import com.example.asistente_medico.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
