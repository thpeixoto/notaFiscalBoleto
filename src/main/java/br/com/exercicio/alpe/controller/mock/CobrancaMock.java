package br.com.exercicio.alpe.controller.mock;


import br.com.exercicio.alpe.entity.NotaFiscal;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


/**
 * <b>Cobrança Mock</b>
 * </p>
 * Para testes da aplicação esta classe serve para enviar uma nota fiscal, quando for boleto
 * @author Thiago Peixoto
 */
@RestController
@RequestMapping("/alpe")
public class CobrancaMock {


    /**
     * <b>boleto</b>
     * </p>
     * Metodo que retorna OK, indicando que recebeu o a nota fiscal
     * @param nota Recebe um JSon dos dados do funcionario
     */
    @PostMapping("/boleto")
    @ResponseStatus(HttpStatus.OK)
    public void boleto(@RequestBody NotaFiscal  nota) { }





}
