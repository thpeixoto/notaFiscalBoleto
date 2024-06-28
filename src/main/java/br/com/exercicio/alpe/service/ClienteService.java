package br.com.exercicio.alpe.service;

import br.com.exercicio.alpe.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


/**
 * <b>BoletoService</b>
 * </p>
 * Busca de clientes em API externa
 * @author Thiago Peixoto
 */
@Service
public class ClienteService   {



    private final WebClient webClient;

    @Autowired
    public ClienteService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .defaultHeaders(headers -> headers.setBasicAuth("user", "alpe"))
                .build();
    }

    public Cliente findClienteByCpf(String cpf) {
        return   buscaApiExternaCliente (  cpf ) ;
    }

    public Cliente buscaApiExternaCliente(String cpf) {
        String url = "http://localhost:8080/alpe/cliente/buscar/" + cpf;

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Cliente.class)
                .block();
    }


}
