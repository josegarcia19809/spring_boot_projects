package com.example.asistente_medico.dto;

public record DoctorInfo(
        String firstName,
        String lastName,
        String specialty,
        String licenseNumber,
        String phone,
        String office
) {
}
