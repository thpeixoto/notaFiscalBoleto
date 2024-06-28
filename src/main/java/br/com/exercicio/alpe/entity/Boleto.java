package br.com.exercicio.alpe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Boleto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "banco", nullable = false)
    private String banco;

    @Column(name = "cnpj_cedente", nullable = false)
    private String cnpjCedente;

    @OneToOne
    @JoinColumn(name = "id_nota")
    @JsonIgnore
    private NotaFiscal notaFiscal;



    @Column(name = "dataEmissao", nullable = false)
    private LocalDate dataEmissao;

    @Column(name = "valorTotalNota", nullable = false)
    private Double valorTotal;


}
