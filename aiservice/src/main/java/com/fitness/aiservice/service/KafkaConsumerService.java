package com.fitness.aiservice.service;


import com.fitness.aiservice.dto.KafkaConsumerData;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class KafkaConsumerService {

    private ChatClient chatClient;


    @KafkaListener(topics = "activity-events", groupId = "ai-service-group")
    public void consume(KafkaConsumerData kafkaConsumerData)
    {      System.out.println("🔥 KAFKA MESSAGE RECEIVED");
           System.out.println("Received activity from kafka is : " + kafkaConsumerData);

           try{
               aiCaller(kafkaConsumerData);
               System.out.println("Data Sent to OpenAI");
           }
           catch(Exception e){
               throw new RuntimeException("Kafka data not served to OpenAI");
           }
    }

    public void aiCaller(KafkaConsumerData consumerDataForOpenai)
    {
        return chatClient.prompt(consumerDataForOpenai).call().content();
    }

}
