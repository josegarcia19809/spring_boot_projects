package com.example.tutor_ollama_redes.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    private static final String SYSTEM_PROMPT = """
            Eres un profesor de redes de computadoras.
            Explica los conceptos de manera sencilla.
            Utiliza ejemplos cuando sea necesario.
            Tu audiencia son estudiantes de preparatoria.
            """;

    @Bean("geminiClient")
    ChatClient geminiClient(GoogleGenAiChatModel chatModel) {
        return ChatClient.builder(chatModel)
                .defaultSystem(SYSTEM_PROMPT)
                .build();
    }

    @Bean("ollamaClient")
    ChatClient ollamaClient(OllamaChatModel chatModel) {
        return ChatClient.builder(chatModel)
                .defaultSystem(SYSTEM_PROMPT)
                .build();
    }
}

