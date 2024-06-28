package br.com.exercicio.alpe.entity.dtos;

/**
 * BoletoDTO
 * Classe de transferencia de dados da nota fiscal
 * @author Thiago Peixoto
 */
public record NotaFiscalDto   (

     String chave,
     String operacao,
     String cpfCnpjCliente,
     String nomeCliente,
     String dataEmissao,
     Double valorTotalNota,
     String formaPagamento

) {}