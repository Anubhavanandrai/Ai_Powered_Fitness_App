package com.fitness.aiservice.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AiServiceConfig {

    @Bean
    public ChatClient aichatClient(ChatClient.Builder cb)
    {
        return cb.build();
    }
}
