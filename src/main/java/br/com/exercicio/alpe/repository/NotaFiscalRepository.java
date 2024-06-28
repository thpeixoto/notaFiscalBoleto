package br.com.exercicio.alpe.repository;


import br.com.exercicio.alpe.entity.NotaFiscal;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * NotaFiscalRepository
 * Responsavel pelos metodos CRUD na tabela de  NotaFiscal
 * @author Thiago Peixoto
 */
public interface NotaFiscalRepository  extends JpaRepository<NotaFiscal, Long> {

    NotaFiscal findFirstNotaFiscalByChave(String chave);

}
