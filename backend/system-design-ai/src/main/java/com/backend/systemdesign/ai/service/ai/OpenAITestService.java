package com.backend.systemdesign.ai.service.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class OpenAITestService {

    private final ChatClient chatClient;

    public OpenAITestService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String test() {

        return chatClient.prompt()
                .user("Say hello")
                .call()
                .content();
    }
}