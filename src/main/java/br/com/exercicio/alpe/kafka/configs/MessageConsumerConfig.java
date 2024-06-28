package br.com.exercicio.alpe.kafka.configs;


import br.com.exercicio.alpe.entity.dtos.BoletoDto;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

/**
 * MessageConsumerConfig
 * Recebimento de mensagens para o Kafka
 * @author Thiago Peixoto
 */
@EnableKafka
@Configuration
public class MessageConsumerConfig {

    @Bean
    public ConsumerFactory<String, BoletoDto> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(GROUP_ID_CONFIG, "alpe");
        props.put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(AUTO_OFFSET_RESET_CONFIG, "earliest");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new
                JsonDeserializer<>(BoletoDto.class));
//        return new DefaultKafkaConsumerFactory<>(props );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, BoletoDto>
    kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, BoletoDto> factory = new
                ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}