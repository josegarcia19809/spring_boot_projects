package com.example.asistente_medico.services;

import com.example.asistente_medico.config.ClientResolver;
import com.example.asistente_medico.dto.analysis.ConditionSummary;
import com.example.asistente_medico.dto.analysis.QueryClassification;
import com.example.asistente_medico.dto.analysis.SymptomAnalysis;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnalysisServiceImpl implements AnalysisService {
    private final ClientResolver clientResolver;

    @Value("classpath:prompts/analisis-estructurado.st")
    private Resource structuredAnalysisResource;

    private PromptTemplate structuredAnalysisTemplate;

    @PostConstruct
    void init() {
        structuredAnalysisTemplate = new PromptTemplate(structuredAnalysisResource);
    }

    @Override
    public ConditionSummary summarizeCondition(String condition, String model) {
        return clientResolver.resolve(model)
                .prompt()
                .user("Proporciona un resumen médico educativo sobre: " + condition)
                .call()
                .entity(ConditionSummary.class);
    }

    @Override
    public List<ConditionSummary> listRelatedConditions(String symptoms, String model) {
        log.info("Listado de condiciones relacionadas - modelo: {} ", model);
        return clientResolver.resolve(model)
                .prompt()
                .user("Lista las 3 condiciones médicas más probables " +
                        "para estos síntomas: " + symptoms
                )
                .call()
                .entity(new ParameterizedTypeReference<>() {
                });
    }

    @Override
    public SymptomAnalysis analyzeSymptoms(String symptoms, String model) {
        log.info("Análisis estructurado de síntomas — modelo: {}", model);

        String message = structuredAnalysisTemplate.render(
                Map.of("sintomas", symptoms)
        );

        return clientResolver.resolve(model)
                .prompt()
                .user(message)
                .call()
                .entity(SymptomAnalysis.class);
    }

    @Override
    public QueryClassification classifyQuery(String query, String model) {
        log.info("Clasificación de consulta — modelo: {}", model);

        return clientResolver.resolve(model)
                .prompt()
                .user("Clasifica la siguiente consulta de un paciente. " +
                        "Determina qué tipo de consulta es y explica brevemente " +
                        "por qué.\n\n" +
                        "Consulta del paciente: \"" + query + "\"")
                .call()
                .entity(QueryClassification.class);
    }
}
