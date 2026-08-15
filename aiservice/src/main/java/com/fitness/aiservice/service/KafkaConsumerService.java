package com.fitness.aiservice.service;


import com.fitness.aiservice.dto.KafkaConsumerData;
import jakarta.annotation.PostConstruct;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    @PostConstruct
    public void test() {
        System.out.println("KafkaConsumerService bean CREATED");
    }
    @KafkaListener(topics = "activity-events", groupId = "ai-service-group")
    public void consume(KafkaConsumerData kafkaConsumerData)
    {      System.out.println("🔥 KAFKA MESSAGE RECEIVED");
        System.out.println("Received activity from kafka is : " + kafkaConsumerData);
    }

}
