package com.example.tutor_redes;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Objects;

// @Component
@RequiredArgsConstructor
public class TestLLMCall implements CommandLineRunner {
    private final ChatModel chatModel;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("-".repeat(100));
        ChatResponse chatResponse = chatModel.call(
                new Prompt("¿Qué es una dirección IP?"));

        String content = Objects.requireNonNull(chatResponse.getResult()).
                getOutput().getText();
        System.out.println("=== RESPUESTA ===");
        System.out.println(content);

        System.out.println("\n=== METADATA ===");
        System.out.println("Modelo: " +
                chatResponse.getMetadata().getModel()
        );

        System.out.println("Tokens de entrada: " +
                chatResponse.getMetadata().getUsage().getPromptTokens()
        );
        System.out.println("Tokens de salida: " +
                chatResponse.getMetadata().getUsage().getCompletionTokens()
        );
        System.out.println("Tokens totales: " +
                chatResponse.getMetadata().getUsage().getTotalTokens()
        );

        System.out.println("Finish reason: " +
                chatResponse.getResult().getMetadata().getFinishReason()
        );
    }
}
