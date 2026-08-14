package com.fitness.aiservice.config;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AiServiceConfig {

    @Bean
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder();
    }

    @Bean
    public WebClient aiServiceWebClient(WebClient.Builder webClientBuilder){
        return webClientBuilder
                .uri("https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-latest:generateContent")
                .build();
    }
}
