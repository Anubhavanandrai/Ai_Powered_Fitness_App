package com.fitness.aiservice.config;


import com.fitness.aiservice.dto.KafkaConsumerData;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, KafkaConsumerData> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerProperties());

    }

    private Map<String, Object> consumerProperties() {

        Map<String, Object> config = new HashMap<>();

        config.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9092"
        );

        config.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                "ai-service-group"
        );

        config.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class
        );

        config.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                JacksonJsonDeserializer.class
        );

        config.put(
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
                "earliest"
        );

        return config;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaConsumerData>
    kafkaListenerContainerFactory(
            ConsumerFactory<String, KafkaConsumerData> consumerFactory) {

        ConcurrentKafkaListenerContainerFactory<String, KafkaConsumerData> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory);
        System.out.println("Creating Kafka Listener Factory");
        return factory;
    }
}