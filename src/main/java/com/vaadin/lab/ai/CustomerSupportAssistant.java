/*
 * Copyright 2025-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vaadin.lab.ai;

import java.util.List;

import com.vaadin.lab.ai.tool.BookingTools;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

/**
 * * @author Christian Tzolov
 */
@Service
public class CustomerSupportAssistant {

	private final ChatClient chatClient;

	// @formatter:off
	public CustomerSupportAssistant(
		ChatClient.Builder chatClientBuilder,
		@Value("classpath:/prompt/system-prompt.txt") Resource systemPrompt,
		ChatMemory chatMemory,
		VectorStore vectorStore,
		BookingTools bookingTools) {

		this.chatClient = chatClientBuilder
				// SYSTEM PROMPT
				.defaultSystem(p -> p.text(systemPrompt).param("ariline-name", "Funnair"))
				.defaultAdvisors(
					// MEMORY
					MessageChatMemoryAdvisor.builder(chatMemory).build(),
					// RAG
					QuestionAnswerAdvisor.builder(vectorStore).build(),
					// GUARDRAILS
					SafeGuardAdvisor.builder() 
						.order(Advisor.DEFAULT_CHAT_MEMORY_PRECEDENCE_ORDER - 100) // ensures it is not put in the chat memory
						.sensitiveWords(List.of(
							"credit card number", "visa number", "password", "ssn", "social security number"))
						.build()
				)
				// TOOLS
				.defaultTools(bookingTools)
				.build();
	}

	public String chat(String chatId, String userMessage) {

		return this.chatClient.prompt()
			.user(userMessage)
			.advisors(a -> a.param(ChatMemory.CONVERSATION_ID, chatId))
			.call()
			.content();
	}
	// @formatter:on

}