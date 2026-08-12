package com.fitness.aiservice.service;


import com.fitness.aiservice.model.Recommendations;
import com.fitness.aiservice.repository.RecommendationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class RecommendationService {

    private final RecommendationRepository recommendationRepository;
    public RecommendationService(RecommendationRepository recommendationRepository)
    {
        this.recommendationRepository =recommendationRepository;
    }

    public Recommendations getActivityRecommendation(String activityId) {
        return recommendationRepository.findByActivityId(activityId)
                .orElseThrow(()->new RuntimeException("No recommendation found for activityId : "+activityId));
    }
    public List<Recommendations> getUserRecommendation(String userId) {
        return recommendationRepository.findByUserId(userId);
    }
}
