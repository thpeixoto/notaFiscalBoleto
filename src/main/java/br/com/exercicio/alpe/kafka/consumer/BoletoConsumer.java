package br.com.exercicio.alpe.kafka.consumer;

import br.com.exercicio.alpe.entity.dtos.BoletoDto;
import br.com.exercicio.alpe.service.BoletoService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


/**
 * BoletoConsumer
 * Direciona para salvar o boleto no banco de dados
 * @author Thiago Peixoto
 */
@AllArgsConstructor
@Component
public class BoletoConsumer {

    private final BoletoService boletoService;

    @KafkaListener(topics = "${topico.alpe.consumer.nome}", groupId = "${topico.alpe.consumer.grupo}",
            containerFactory = "kafkaListenerContainerFactory")
    public void consumerProducerBoleto(BoletoDto boletoDTO) {
        try {
           boletoService.insert(boletoDTO);
        } catch (Exception exception) {
            throw new RuntimeException("Erro ao consumir mensagem do kafka " + exception);
        }
    }
}