package br.com.exercicio.alpe.service;


import java.util.Optional;


/**
 * BasicService
 * Inteface para padronizar as classes de serviço
 * @author Thiago Peixoto
 */
public interface   BasicService<Dto, C> {


    public Optional<C> insert(Dto objectInsert);

    public Optional<C> findById(Long id);

    public boolean delete(Long id);


}