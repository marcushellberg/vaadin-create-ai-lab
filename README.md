# Vaadin + Spring AI Lab

This is the completed application for a hands-on lab at Vaadin Create 2025. The application demonstrates how to build an AI-powered flight booking customer support system using Vaadin Flow and Spring AI.

## Overview

This application showcases a customer support chatbot for "Funnair", a fictional airline. The chatbot assists customers with managing their flight bookings through natural language conversations. The UI features a split view with a chat interface on the left and a real-time grid of bookings on the right.

## Spring AI Concepts Covered

This lab demonstrates the following Spring AI features:

### 1. **Chat Client**
- Uses `ChatClient` to interact with OpenAI's language models
- Implements streaming responses for a responsive chat experience
- Demonstrates prompt engineering with system prompts to define the assistant's personality and behavior

### 2. **Function Calling (Tools)**
- Implements `@Tool` annotations to expose Java methods as functions the AI can call
- Demonstrates both simple tools (get/change/cancel bookings) and interactive tools (seat selection with UI)
- Shows how to integrate tool responses back into the conversation flow

### 3. **Chat Memory (Conversation Context)**
- Uses `MessageChatMemoryAdvisor` to maintain conversation history
- Implements per-conversation memory using unique chat IDs
- Enables the assistant to reference previous messages and maintain context

### 4. **RAG (Retrieval-Augmented Generation)**
- Configures a vector store using `SimpleVectorStore` for document storage
- Implements document ingestion from text files (terms of service)
- Uses `QuestionAnswerAdvisor` to enhance responses with relevant information from the knowledge base
- Demonstrates text splitting and embedding for efficient document retrieval

### 5. **Advisors**
- Shows how to chain multiple advisors (chat memory and vector store)
- Demonstrates advisor configuration with custom parameters

## Prerequisites

- Java 25 or later
- Maven 3.6+
- OpenAI API key

## Running the Application

1. **Set your OpenAI API key** as an environment variable:
   ```bash
   export OPENAI_API_KEY=your-api-key-here
   ```

2. **Run the application** using Maven:
   ```bash
   ./mvnw spring-boot:run
   ```

3. **Access the application** at `http://localhost:8080`
   - The application will automatically open in your default browser

## Using the Application

1. Start a conversation by typing in the chat input at the bottom of the left panel
2. Ask about bookings using information like:
   - Booking number: `ABC123`
   - Customer name: `John Doe`
3. Try various operations:
   - Check booking status
   - Change booking dates
   - Cancel bookings
   - Change seats (opens an interactive seat selector)
   - Ask questions about terms of service
4. Watch the bookings grid on the right update in real-time as changes are made

## Project Structure

- `FlightBookingView.java` - Main UI with chat interface and bookings grid
- `CustomerSupportAssistant.java` - Spring AI chat client configuration with advisors
- `BookingTools.java` - Tool definitions for booking operations
- `VectorDbConfig.java` - Vector store configuration for RAG
- `DocumentProcessor.java` - Document ingestion into vector store
- `src/main/resources/rag/` - Knowledge base documents for RAG

## Technologies Used

- **Vaadin** - Full-stack web framework for the UI
- **Spring Boot** - Application framework
- **Spring AI** - AI integration framework
- **OpenAI** - Language model provider

## Production Build

To create a production build:

```bash
./mvnw clean package -Pproduction
```

Then run the JAR:

```bash
java -jar target/ai-0.0.1-SNAPSHOT.jar
```