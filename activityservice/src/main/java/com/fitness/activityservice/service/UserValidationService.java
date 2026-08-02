package com.fitness.activityservice.service;


import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import org.springframework.web.reactive.function.client.WebClientResponseException;


@Service
public class UserValidationService {


    private final WebClient userServiceWebClient;
    public UserValidationService(WebClient userServiceWebClient)
    {
        this.userServiceWebClient = userServiceWebClient;
    }

    public boolean validateUserID(String id){
        try {
            return Boolean.TRUE.equals(userServiceWebClient.get()
                    .uri("/api/user/{id}/validate", id)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block());
        }
        catch (WebClientResponseException e)
        {
            if (e.getStatusCode().value() == 404)
            {
            throw new RuntimeException("User not found");
            }
            else{
                throw new RuntimeException("Userservice not available" ,e);
                }
        }

        }
    }