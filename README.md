# Vaadin + Spring AI Lab

This is a hands-on lab for Vaadin Create 2025. In this lab, you'll build an AI-powered flight booking customer support system using Vaadin Flow and Spring AI.

## Overview

This application showcases a customer support chatbot for "Funnair", a fictional airline. The chatbot assists customers with managing their flight bookings through natural language conversations. The UI features a split view with a chat interface on the left and a real-time grid of bookings on the right.

## What You'll Learn

In this lab, you'll implement the following Spring AI features:

### 1. **Chat Client**
- Use `ChatClient` to interact with AI language models
- Implement streaming responses for a responsive chat experience
- Apply prompt engineering with system prompts to define the assistant's personality and behavior

### 2. **Function Calling (Tools)**
- Implement `@Tool` annotations to expose Java methods as functions the AI can call
- Create both simple tools (get/change/cancel bookings) and interactive tools (seat selection with UI)
- Integrate tool responses back into the conversation flow

### 3. **Chat Memory (Conversation Context)**
- Use `MessageChatMemoryAdvisor` to maintain conversation history
- Implement per-conversation memory using unique chat IDs
- Enable the assistant to reference previous messages and maintain context

### 4. **RAG (Retrieval-Augmented Generation)**
- Configure a vector store using `SimpleVectorStore` for document storage
- Implement document ingestion from text files (terms of service)
- Use `QuestionAnswerAdvisor` to enhance responses with relevant information from the knowledge base
- Demonstrate text splitting and embedding for efficient document retrieval

### 5. **Advisors**
- Chain multiple advisors (chat memory and vector store)
- Configure advisors with custom parameters

## Prerequisites

- Java 25 or later
- Maven 3.6+
- An IDE (IntelliJ IDEA, Eclipse, or VS Code recommended)
- Anthropic API key (will be provided during the lab)

## Getting Started

### 1. Setup Your IDE

For the best development experience, install the Vaadin plugin for your IDE:

- **IntelliJ IDEA**: Install the [Vaadin plugin](https://plugins.jetbrains.com/plugin/23758-vaadin) from the marketplace
- **VS Code**: Install the [Vaadin extension](https://marketplace.visualstudio.com/items?itemName=vaadin.vaadin-vscode)
- **Eclipse**: Install the [Vaadin plugin](https://marketplace.eclipse.org/content/vaadin-plugin-eclipse)

### 2. Configure Your API Key

Set your Anthropic API key as an environment variable:

```bash
export ANTHROPIC_API_KEY=your-api-key-here
```

Or add it to your IDE's run configuration environment variables.

### 3. Run the Application

**Recommended**: Use your IDE with hotswap enabled:

1. Open `src/main/java/com/vaadin/lab/AiApplication.java`
2. Run it with hotswap enabled (the Vaadin plugin will help with this)
3. The application will open at `http://localhost:8080`

**Alternative**: Run using Maven:

```bash
./mvnw spring-boot:run
```

With hotswap enabled, you can make changes to your Java code and see them reflected immediately without restarting the application.
