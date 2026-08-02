package com.fitness.activityservice.config;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder();
    }

    //Bean is injected by its type not name thats why we use "ClassName" this is also a type.
    // so whenever we search for a bean we search for the type first then
    //if we have multiple beans of same type then we use qualifier.

    // private final WebClient webclient; will be injected
    @Bean
    public WebClient userServciceWebClient(WebClient.Builder webClientBuilder){
    return webClientBuilder
            .baseUrl("http://userservice")
            .build();
    }
}
