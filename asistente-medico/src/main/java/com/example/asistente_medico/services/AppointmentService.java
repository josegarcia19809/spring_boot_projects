package com.example.asistente_medico.services;

import com.example.asistente_medico.dto.AppointmentInfo;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {
    List<AppointmentInfo> findAvailableAppointments(String specialty, LocalDate date);
}
