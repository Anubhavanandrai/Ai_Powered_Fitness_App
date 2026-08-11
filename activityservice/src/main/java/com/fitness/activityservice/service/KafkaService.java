package com.fitness.activityservice.service;


import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaService {

    private final KafkaTemplate<String, String>  kafkaTemplate;
    private final String TOPIC="activity-events";

    public KafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendActivityEvent(String message) {
        CompletableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(TOPIC, message);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                System.out.println("Failed to send message: {}"+ ex);
            } else {
                System.out.println("Message sent to partition " + result.getRecordMetadata().partition() +
                        " at offset " + result.getRecordMetadata().offset());
            }
        });

}}
