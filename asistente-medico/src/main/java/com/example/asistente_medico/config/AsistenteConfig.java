package com.example.asistente_medico.config;


import com.example.asistente_medico.tools.AppointmentSearchTool;
import com.example.asistente_medico.tools.DoctorInfoTool;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chroma.vectorstore.ChromaApi;
import org.springframework.ai.chroma.vectorstore.ChromaVectorStore;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import java.time.LocalDate;

@Configuration
@RequiredArgsConstructor
public class AsistenteConfig {

    @Value("classpath:prompts/system-prompt.st")
    private Resource systemPromptResource;

    private final AppointmentSearchTool appointmentSearchTool;
    private final DoctorInfoTool doctorInfoTool;
    private final ChatMemory chatMemory;

    @Bean("geminiClient")
    ChatClient geminiClient(
            GoogleGenAiChatModel chatModel,
            @Qualifier("ollamaVectorStore") VectorStore vectorStore) throws IOException {

        String systemPrompt = systemPromptResource.getContentAsString(StandardCharsets.UTF_8)
                .replace("{currentDate}", LocalDate.now().toString());

        return ChatClient.builder(chatModel)
                .defaultSystem(systemPrompt)
                .defaultTools(appointmentSearchTool, doctorInfoTool)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build(),
                        QuestionAnswerAdvisor.builder(vectorStore)
                                .searchRequest(SearchRequest.builder()
                                        .similarityThreshold(0.7).topK(3).build())
                                .promptTemplate(new PromptTemplate("""
                                Contexto de documentos médicos (usalo si es relevante para la pregunta):
                                {question_answer_context}
                                
                                Si la información no está en los documentos, podés usar tus herramientas
                                o tu conocimiento general para responder.
                                """))
                                .build()
                )
                .build();
    }

    @Bean("ollamaClient")
    ChatClient ollamaClient(
            OllamaChatModel chatModel,
            @Qualifier("ollamaVectorStore") VectorStore vectorStore) throws IOException {
        String systemPrompt = systemPromptResource.getContentAsString(StandardCharsets.UTF_8)
                .replace("{currentDate}", LocalDate.now().toString());

        return ChatClient.builder(chatModel)
                .defaultSystem(systemPrompt)
                .defaultTools(appointmentSearchTool, doctorInfoTool)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build(),
                        QuestionAnswerAdvisor.builder(vectorStore)
                                .searchRequest(SearchRequest.builder()
                                        .similarityThreshold(0.7).topK(3).build())
                                .promptTemplate(new PromptTemplate("""
                                Contexto de documentos médicos (usalo si es relevante para la pregunta):
                                {question_answer_context}
                                
                                Si la información no está en los documentos, podés usar tus herramientas
                                o tu conocimiento general para responder.
                                """))
                                .build()
                )
                .build();
    }

    @Bean("ollamaVectorStore")
    VectorStore ollamaVectorStore(
            @Qualifier("ollamaEmbeddingModel") EmbeddingModel embedding,
            ChromaApi chromaApi) {
        return ChromaVectorStore.builder(chromaApi, embedding)
                .collectionName("asistente_medico_ollama")
                .initializeSchema(true)
                .build();
    }

    @Bean
    ChromaApi chromaApi(@Value("${spring.ai.vectorstore.chroma.url}") String chromaUrl) {
        return ChromaApi.builder()
                .baseUrl(chromaUrl)
                .build();
    }
}
