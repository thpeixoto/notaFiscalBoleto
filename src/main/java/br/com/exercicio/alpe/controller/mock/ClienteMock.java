package br.com.exercicio.alpe.controller.mock;


import br.com.exercicio.alpe.entity.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;



/**
 * <b>Cliente Mock</b>
 * </p>
 * Para testes da aplicação esta classe serve para validar se um cliente existe em um serviço externo
 * @author Thiago Peixoto
 */
@RestController
@RequestMapping("/alpe/cliente")
public class ClienteMock {

    private static Map<String, Cliente> clientesMock = new HashMap<>();

    static{
                inicializaClientes();
    }

    /**
     * <b>buscar</b>
     * </p>
     * Chamada para o método buscar
     * @param cpf Recebe o cpf do cliente para busca
     * @return  Retorna o Cliente
     */
    @GetMapping("/buscar/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente buscar(@PathVariable("cpf") String cpf) {
        return  clientesMock.get(cpf) ;

    }



    private static void inicializaClientes() {

        clientesMock.put("29825401850", new Cliente(1L, "Thiago Peixoto", "29825401850"));
        clientesMock.put("12345678910", new Cliente(2L, "João Lopes", "12345678910"));
        clientesMock.put("01987654321", new Cliente(3L, "Jose de Alencar", "01987654321"));
        clientesMock.put("25340018687", new Cliente(4L, "Rafael Gabriel Silva", "25340018687"));



    }



}
