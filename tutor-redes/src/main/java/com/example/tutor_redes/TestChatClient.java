package com.example.tutor_redes;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// @Component
@RequiredArgsConstructor
public class TestChatClient implements CommandLineRunner {

    private final ChatClient chatClient;

    @Override
    public void run(String... args) throws Exception {

        String response = chatClient
                .prompt("¿Qué es una red WIFI?")
                .call()
                .content();

        System.out.println(response);
    }
}