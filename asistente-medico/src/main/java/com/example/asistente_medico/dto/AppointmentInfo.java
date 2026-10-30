package com.example.asistente_medico.dto;

public record AppointmentInfo(
        String doctorName,
        String specialty,
        String date,
        String time
) {
}
