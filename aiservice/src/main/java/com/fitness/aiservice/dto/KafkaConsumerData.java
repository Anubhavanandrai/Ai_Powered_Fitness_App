package com.fitness.aiservice.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.Map;

@Data
public class KafkaConsumerData
{
        private String userId;
        private String type;
        private Integer duration;
        private Integer caloriesBurned;
        private LocalDateTime startTime;
        private Map<String, Object> additionalMetrics;
    }

