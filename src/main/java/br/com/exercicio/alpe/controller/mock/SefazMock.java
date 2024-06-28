package br.com.exercicio.alpe.controller.mock;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Random;


/**
 * <b>API Sefaz</b>
 * </p>
 * Mock para validação da nota fiscal, com a finalidade de testar a aplicação
 * @author Thiago Peixoto
 */
@RestController
@RequestMapping("/gov")
public class SefazMock {



    /**
     * <b>validar</b>
     * </p>
     * Retorna que a nota fiscal é valida em 80% dos casos
     * @param chave Recebe a chave da nota
     * @return  True nota valida, False inválida
     */
    @GetMapping("/validar/{chave}")
    @ResponseStatus(HttpStatus.OK)
    public void validar(@PathVariable("chave") String chave) {
        if (!notaValida()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nota inválida");
        }
    }


    private boolean notaValida() {
        return new Random().nextDouble() < 0.8;
    }




}
