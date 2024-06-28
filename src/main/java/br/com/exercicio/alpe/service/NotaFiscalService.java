package br.com.exercicio.alpe.service;

import br.com.exercicio.alpe.entity.Cliente;
import br.com.exercicio.alpe.entity.NotaFiscal;
import br.com.exercicio.alpe.entity.dtos.NotaFiscalDto;
import br.com.exercicio.alpe.entity.enums.FormaPagamentoEnum;
import br.com.exercicio.alpe.entity.enums.OperacaoEnum;
import br.com.exercicio.alpe.repository.NotaFiscalRepository;
import br.com.exercicio.alpe.util.Datas;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * <b>NotaFiscalService</b>
 * </p>
 * Regras de negocio para salvar e buscar notas fiscais
 * @author Thiago Peixoto
 */
@Service
public class NotaFiscalService implements BasicService<NotaFiscalDto, NotaFiscal> {


    private final NotaFiscalRepository repository;
    private final WebClient webClient;
    private final ClienteService clienteService;

    @Autowired
    public NotaFiscalService(NotaFiscalRepository repository, ClienteService clienteService) {
        this.repository = repository;
        this.clienteService = clienteService;
        this.webClient = WebClient.builder()
                .defaultHeaders(headers -> headers.setBasicAuth("user", "alpe"))
                .build();
    }

    @Override
    @Transactional
    public Optional<NotaFiscal> insert(NotaFiscalDto objectInsert) {
        NotaFiscal notaFiscal = parseDTO(objectInsert);
        Cliente clienteBusca = buscaCliente(objectInsert.cpfCnpjCliente());
        notaFiscal.setCliente(clienteBusca);
        return Optional.ofNullable(repository.save(notaFiscal));
    }

    @Override
    public Optional<NotaFiscal> findById(Long id) {
        return repository.findById(id);
    }

    public Optional<NotaFiscal> findByChave(String chave) {
        return Optional.ofNullable(repository.findFirstNotaFiscalByChave(chave));
    }

    @Override
    public boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }

    private NotaFiscal parseDTO(NotaFiscalDto dto) {
        NotaFiscal resultado = new NotaFiscal();
        resultado.setChave(dto.chave());
        resultado.setOperacao(OperacaoEnum.valueOf(dto.operacao().trim()));
        resultado.setDataEmissao(Datas.stringToLocalDate(dto.dataEmissao(), "yyyy-MM-dd"));
        resultado.setValorTotalNota(dto.valorTotalNota());
        resultado.setFormaPagamento(FormaPagamentoEnum.valueOf(dto.formaPagamento().trim()));
        return resultado;
    }

    private Cliente buscaCliente(String cpf) {
        return clienteService.findClienteByCpf(cpf);
    }

    public NotaFiscal trataInclusaoNota(NotaFiscalDto objectInsert) {
        if (!notaFiscalValida(objectInsert.chave())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Nota fiscal não validada pela SEFAZ");
        }

        NotaFiscal nota = this.insert(objectInsert).orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao salvar a nota fiscal"));

        enviaSistemaCobranca(nota);

        return nota;
    }

    private void enviaSistemaCobranca(NotaFiscal nota) {
        if (nota.getFormaPagamento() != FormaPagamentoEnum.Boleto) return;

        String url = "http://localhost:8080/alpe/boleto";

        webClient.get()
                .uri(url)
                .retrieve()
                .toBodilessEntity()
                .subscribe();
    }

    private boolean notaFiscalValida(String chave) {
        String url = "http://localhost:8080/gov/validar/" + chave;

        return webClient.get()
                .uri(url)
                .exchangeToMono(response -> Mono.just(response.statusCode()))
                .block() == HttpStatus.OK;
    }
}
