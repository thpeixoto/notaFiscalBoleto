package br.com.exercicio.alpe.controller;


import br.com.exercicio.alpe.entity.NotaFiscal;
import br.com.exercicio.alpe.entity.dtos.NotaFiscalDto;
import br.com.exercicio.alpe.service.NotaFiscalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


/**
 * <b>API NotaFiscalController </b>
 * </p>
 * Operações CRUD na nota Fiscal
 * @author Thiago Peixoto
 */
@RestController
@RequestMapping("/alpe/notafiscal")
public class NotaFiscalController {


    @Autowired
    private NotaFiscalService notaFiscalService ;

    /**
     * <b>salvar</b>
     * </p>
     * Chamada para o método Salvar
     * @param objectInsert NotaDTO que deve ser inserida no banco de dados
     * @return  Retorna a Nota Fiscal com o novo ID, ou os erros 400 ou 403
     */
    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public NotaFiscal salvar(@RequestBody NotaFiscalDto objectInsert) {

        if ( objectInsert == null || objectInsert.chave() == null ) {
            throw  retornoParametroInvalido();
        }

    return notaFiscalService.trataInclusaoNota (objectInsert);


    }





    /**
     * <b>buscar</b>
     * </p>
     * Chamada para o método buscar
     * @param id Recebe a chave primaria do registro da nota fiscal
     * @return  Retorna a nota fiscal com o novo ID, ou os erros 404
     */
    @GetMapping("/buscar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public NotaFiscal buscar(@PathVariable("id") Long id) {
        return notaFiscalService.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Falha ao buscar a nota fiscal. Não existe  com este ID."));
    }

    /**
     * <b>excluir</b>
     * </p>
     * Chamada para o método excluir
     * @param id Recebe a chave primaria do registro de nota fiscal que deseja excluir
     */
    @DeleteMapping("/excluir/{id}")
    public void excluir(@PathVariable("id") Long id) {
        if (!notaFiscalService.delete(id))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Falha ao excluir a nota fiscal. Possivel causa: não existe a nota fiscal com ID." + id);
    }


    /**
     * <b>health</b>
     * </p>
     * Chamada para o método health, teste se a API esta ativa
     */
    @GetMapping("/health")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> health( ) {
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    private ResponseStatusException retornoParametroInvalido() {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parametro invalido /n O JSon para uso da API deverá seguir o seguinte exemplo: /n " +
                " {\n" +
                "    \"chave\": \"123456\",\n" +
                "    \"operacao\": \"Venda\",\n" +
                "    \"cpfCnpjCliente\": \"29825401850\",\n" +
                "    \"nomeCliente\": \"Thiago Peixoto\",\n" +
                "    \"dataEmissao\": \"2024-06-20\",\n" +
                "    \"valorTotalNota\": \"234.10\",\n" +
                "    \"formaPagamento\": \"Boleto\"\n" +
                "}" +
                "" );
    }


}
