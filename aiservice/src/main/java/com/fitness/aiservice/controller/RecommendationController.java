package com.fitness.aiservice.controller;


import com.fitness.aiservice.model.Recommendations;
import com.fitness.aiservice.service.RecommendationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService)
    {
        this.recommendationService=recommendationService;
    }

    @GetMapping("/userrecommendation/{userId}")
    public ResponseEntity<List<Recommendations>> getUserRecommendation(@PathVariable String userId) {
        System.out.println("Inside ai service controller");
        return ResponseEntity.ok(recommendationService.getUserRecommendation(userId));
    }
    @GetMapping("/activityrecommendation/{activityId}")
    public ResponseEntity<Recommendations> getActivityRecommendation(@PathVariable String activityId) {
        return ResponseEntity.ok(recommendationService.getActivityRecommendation(activityId));
    }

}
