package br.com.exercicio.alpe.controller.mock;


import br.com.exercicio.alpe.entity.dtos.BoletoDto;
import br.com.exercicio.alpe.kafka.producer.BoletoProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


/**
 * <b>Boleto Mock</b>
 * </p>
 * Para testes da aplicação esta classe serve para enviar um boleto no Kafka
 * @author Thiago Peixoto
 */
@RestController
@RequestMapping("/boleto")
public class BoletoMock {


    @Autowired
    private BoletoProducer boletoProducer;

    /**
     * <b>Enviar</b>
     * </p>
     * Envio de um boleto no formato, indicado no arquivo LeiaMe
     * @param boleto
     */
    @PostMapping("/envio")
    @ResponseStatus(HttpStatus.OK)
    public void enviar(@RequestBody BoletoDto boleto) {
        if (!envioBoletoKafka(boleto)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Boleto não enviado para a mensageria");
        }
    }


    private boolean envioBoletoKafka(BoletoDto boleto) {

       return  boletoProducer.enviaBoletoKafka(boleto);
    }




}
