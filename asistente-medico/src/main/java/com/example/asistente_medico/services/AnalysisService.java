package com.example.asistente_medico.services;

import com.example.asistente_medico.dto.analysis.ConditionSummary;
import com.example.asistente_medico.dto.analysis.QueryClassification;
import com.example.asistente_medico.dto.analysis.SymptomAnalysis;

import java.util.List;

public interface AnalysisService {
    ConditionSummary summarizeCondition(String condition, String model);
    List<ConditionSummary> listRelatedConditions(String condition, String model);
    SymptomAnalysis analyzeSymptoms(String symptoms, String model);
    QueryClassification classifyQuery(String query, String model);
}
