package br.com.exercicio.alpe.entity.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * BoletoDTO
 * Classe de transferencia de dados do boleto
 * @author Thiago Peixoto
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BoletoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String banco;
    private CedenteDto cedente;
    private SacadoDto sacado;
    private DadosBoletoDto dados_boleto;


    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class CedenteDto {
        private String nome;
        private String cpf_cnpj;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class SacadoDto {
        private String nome;
        private String cpf_cnpj;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class DadosBoletoDto {
        private Double valor;
        private String vencimento;
        private String nosso_numero;
        private String numero_documento;
        private String data_documento;
        private String especie;
        private String aceite;

    }
}
