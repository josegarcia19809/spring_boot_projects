package com.example.asistente_medico.services;


import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@Service
@Slf4j
public class DocumentIngestionService {

    private final VectorStore ollamaVectorStore;

    @Value("classpath:docs/farmacologia-ibuprofeno.pdf")
    private Resource ibuprofenoDoc;

    @Value("classpath:docs/guia-hipertension-medassistant.pdf")
    private Resource hipertensionDoc;

    @Value("classpath:docs/protocolo-atencion-cardiovascular.pdf")
    private Resource cardiovascularDoc;

    @Value("classpath:docs/mal-de-amores.pdf")
    private Resource malAmoresDoc;

    public DocumentIngestionService(
            @Qualifier("ollamaVectorStore") VectorStore ollamaVectorStore
    ) {
        this.ollamaVectorStore = ollamaVectorStore;
    }

    @PostConstruct
    public void ingest() {
        log.info("Iniciando ingestión de documentos médicos...");
        List<Document> allDocuments = new ArrayList<>();
        allDocuments.addAll(readDocument(ibuprofenoDoc));
        allDocuments.addAll(readDocument(hipertensionDoc));
        allDocuments.addAll(readDocument(cardiovascularDoc));
        allDocuments.addAll(readDocument(malAmoresDoc));

        TokenTextSplitter splitter = TokenTextSplitter.builder()
                .withChunkSize(800)
                .withMaxNumChunks(1000)
                .build();

        List<Document> chunks = splitter.apply(allDocuments);

        log.info("Documentos divididos en {} fragmentos", chunks.size());

        log.info("Ingiriendo en collection Ollama...");
        ollamaVectorStore.add(chunks);
        probarBusqueda();
    }

    public void probarBusqueda() {

        var resultados = ollamaVectorStore.similaritySearch(
                SearchRequest.builder()
                        .query("Recomendaciones sobre el consumo de sal en pacientes hipertensos")
                        .topK(5)
                        .build()
        );

        System.out.println("Resultados: " + resultados.size());

        resultados.forEach(documento ->
                System.out.println(documento.getText())
        );
    }


    private List<Document> readDocument(Resource resource) {
        log.info("Leyendo documento: {}", resource.getFilename());
        TikaDocumentReader reader = new TikaDocumentReader(resource);
        return reader.get();
    }

}