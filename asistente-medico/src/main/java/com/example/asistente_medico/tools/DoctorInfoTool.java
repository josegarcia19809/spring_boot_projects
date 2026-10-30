package com.example.asistente_medico.tools;

import com.example.asistente_medico.dto.DoctorInfo;
import com.example.asistente_medico.services.DoctorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DoctorInfoTool {
    private final DoctorService doctorService;

    @Tool(description = "Buscar información de un médico por nombre o especialidad. " +
            "Usar cuando el usuario pregunte por datos de un doctor, quién atiende una " +
            "especialidad, o información de contacto de un médico.")
    public List<DoctorInfo> searchDoctors(
            @ToolParam(description = "Solo el apellido del médico o el nombre de " +
                    "la especialidad, sin títulos como Dr. o Dra.") String query) {

        log.info("Tool invocada — searchDoctors: query={}", query);
        List<DoctorInfo> doctors = doctorService.searchDoctors(query);

        log.info("Médicos encontrados: {}", doctors.size());
        log.info("Resultado de la herramienta: {}", doctors);

        return doctors;
    }
}
