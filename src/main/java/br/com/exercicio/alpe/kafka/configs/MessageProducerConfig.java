package br.com.exercicio.alpe.kafka.configs;


import br.com.exercicio.alpe.entity.dtos.BoletoDto;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;


/**
 * MessageConsumerConfig
 * Envio de mensagens para o Kafka
 * @author Thiago Peixoto
 */
@Configuration
public class MessageProducerConfig {

    @Bean
    public ProducerFactory<String, BoletoDto> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaProducerFactory(configProps, new StringSerializer(), new JsonSerializer( ));
    }

    @Bean
    public KafkaTemplate<String, BoletoDto> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
