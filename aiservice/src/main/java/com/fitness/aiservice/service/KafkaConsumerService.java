package com.fitness.aiservice.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitness.aiservice.dto.KafkaConsumerData;
import com.fitness.aiservice.model.Recommendations;
import lombok.AllArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;




@Service
@AllArgsConstructor
public class KafkaConsumerService {

    private ChatClient chatClient;
    private ObjectMapper objectMapper;


    @KafkaListener(topics = "activity-events", groupId = "ai-service-group")
    public void consume(KafkaConsumerData kafkaConsumerData)
    {
           System.out.println("🔥 KAFKA MESSAGE RECEIVED");
           System.out.println("Received activity from kafka is : " + kafkaConsumerData);
           try {
               System.out.println("Data ready  to send");
               String json = aiCaller(kafkaConsumerData);
               System.out.println("Data received from aicaller is : "+json);
               Recommendations  airecommend = objectMapper.readValue(json, Recommendations.class);
               System.out.println("Response received from AI is  : " + airecommend);
           }
           catch(Exception e){
               throw new RuntimeException("Kafka data not served to OpenAI");
            }
    }

    public String aiCaller (KafkaConsumerData consumerDataForOpenai) throws JsonProcessingException {

            String json = objectMapper.writeValueAsString(consumerDataForOpenai);
        System.out.println("Ready to send prompt ");
            String prompt = """
                    Analyze this activity data and genere
                    ate result contaning activity Type, genereate recommendations , List of improvements and suggesttion and safety measures to be taken.Give response in JSON formate.
                    Data : %s
                    """.formatted(json);
        System.out.println("Ai caller has received prompt: "+prompt);
            return chatClient.prompt(prompt).call().content();

    }

}
