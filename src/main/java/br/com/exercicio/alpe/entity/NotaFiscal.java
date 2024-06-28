package br.com.exercicio.alpe.entity;

import br.com.exercicio.alpe.entity.enums.FormaPagamentoEnum;
import br.com.exercicio.alpe.entity.enums.OperacaoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class NotaFiscal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "chave", nullable = false,  unique=true)
    private String chave;

    @Column(name = "operacao", nullable = false)
    private OperacaoEnum operacao;


    @Column(name = "id_cliente_externo", nullable = false)
    private Long id_cliente_externo;

    @Transient
    private Cliente cliente;

    @Column(name = "dataEmissao", nullable = false)
    private LocalDate dataEmissao;

    @Column(name = "valorTotalNota", nullable = false)
    private Double valorTotalNota;

    @Column(name = "formaPagamento", nullable = false)
    private FormaPagamentoEnum formaPagamento;


    @OneToOne(mappedBy = "notaFiscal", cascade = CascadeType.ALL)
    private Boleto boleto;

    public void setCliente(Cliente cliente) {
        this.id_cliente_externo = (cliente != null ? cliente.getId() : null);
        this.cliente = cliente;
    }
}
