package com.example.tutor_redes.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TutorConfig {
    @Bean
    ChatClient chatClient(ChatClient.Builder builder){
        return builder.build();
    }
}
