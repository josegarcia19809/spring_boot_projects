package com.example.asistente_medico.services;

import com.example.asistente_medico.dto.DoctorInfo;

import java.util.List;

public interface DoctorService {
    List<DoctorInfo> searchDoctors(String query);
}
