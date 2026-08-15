package com.fitness.aiservice.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AiServiceConfig {

    @Bean
    public WebClient.Builder apiBuilder(){
        return WebClient.builder();
    }

    @Bean
    public WebClient aiServiceWebClient(WebClient.Builder webClientBuilder)
    {
        return webClientBuilder
                .baseUrl("https://api.openai.com")
                .build();
    }
}
