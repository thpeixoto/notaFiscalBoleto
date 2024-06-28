package br.com.exercicio.alpe.repository;


import br.com.exercicio.alpe.entity.Boleto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * BoletoRepository
 * Responsavel pelos metodos CRUD na tabela de  Boleto
 * @author Thiago Peixoto
 */
public interface BoletoRepository extends JpaRepository<Boleto, Long> {


}
