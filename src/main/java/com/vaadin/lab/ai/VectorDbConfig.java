package com.vaadin.lab.ai;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VectorDbConfig {

    @Bean
    public VectorStore vectorStore(EmbeddingModel embeddingClient) {
        return SimpleVectorStore.builder(embeddingClient).build();
    }
}
