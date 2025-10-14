package com.vaadin.lab.ai;

import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
public class DocumentProcessor {

    // In the real world, ingesting documents would often happen separately, on a CI server or similar
    @Bean
    CommandLineRunner ingestDocsForSpringAi(VectorStore vectorStore) {

        // Ingest all txt files from the rag directory into the vector store
        return args -> {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

            TokenTextSplitter splitter = new TokenTextSplitter(
                30, // Chunk size
                20, // Overlap size
                1, // Min chunk size
                10000, // Max chunk size
                true // Add metadata with the source file name
            );

            // Process each document
            for (Resource resource : resolver.getResources("classpath:rag/*.txt")) {
                System.out.println("Ingesting document: " + resource.getFilename());
                vectorStore.write(splitter.transform(new TextReader(resource).read()));
            }
        };
    }
}
