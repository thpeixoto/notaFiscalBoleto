package br.com.exercicio.alpe.service;

import br.com.exercicio.alpe.entity.Boleto;
import br.com.exercicio.alpe.entity.NotaFiscal;
import br.com.exercicio.alpe.entity.dtos.BoletoDto;
import br.com.exercicio.alpe.repository.BoletoRepository;
import br.com.exercicio.alpe.util.Datas;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * <b>BoletoService</b>
 * </p>
  * Regras de negocio para salvar os boletos
 * @author Thiago Peixoto
 */
@Service
public class BoletoService implements BasicService<BoletoDto, Boleto> {

    public final BoletoRepository repository;

    public BoletoService(BoletoRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private  NotaFiscalService notaFiscalService;




    @Override
    @Transactional
    public Optional<Boleto> insert(BoletoDto objectInsert) {
        Boleto boleto = parseDTO(objectInsert);
        boleto.setNotaFiscal(buscaNota(objectInsert.getDados_boleto().getNumero_documento()).get());
        Boleto salvo = repository.save(boleto);
        return  Optional.ofNullable(salvo);
    }



    @Override
    public Optional<Boleto> findById(Long id) {
          return   repository.findById( id );

    }



    @Override
    public boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }

    private Boleto parseDTO ( BoletoDto dto ) {
        Boleto resultado = new Boleto();
        resultado.setBanco(dto.getBanco() );
        resultado.setCnpjCedente(dto.getCedente().getCpf_cnpj() );
        resultado.setDataEmissao(Datas.stringToLocalDate(dto.getDados_boleto().getData_documento(), "yyyy-MM-dd"));
        resultado.setValorTotal(dto.getDados_boleto().getValor());
        return resultado;
    }

    private Optional<NotaFiscal> buscaNota(String chave) {
        return notaFiscalService.findByChave(chave);
    }

}
