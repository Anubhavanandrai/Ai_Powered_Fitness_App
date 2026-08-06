package com.fitness.aiservice.service;


import com.fitness.aiservice.repository.RecommendationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RecommendationService {

    @Autowired
    private final RecommendationRepository recommendationRepository;
}
