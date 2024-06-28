package br.com.exercicio.alpe.kafka.producer;

import br.com.exercicio.alpe.entity.dtos.BoletoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


/**
 * BoletoProducer
 * Envio de boleto via Kafka
 * @author Thiago Peixoto
 */
@Component
public class BoletoProducer {

    @Value("${topico.alpe.consumer.nome}")
    private String topico;


    @Autowired
    private final KafkaTemplate<String, BoletoDto> kafkaTemplate;


    public BoletoProducer(KafkaTemplate<String, BoletoDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public boolean enviaBoletoKafka( final BoletoDto boleto) {
        try {
            kafkaTemplate.send(topico, boleto);
            return true;
        }catch (Exception e ) {
            throw new RuntimeException( "Erro ao enviar boleto para a mensageria");
        }
    }
}
