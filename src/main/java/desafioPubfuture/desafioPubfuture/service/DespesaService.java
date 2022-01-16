package desafioPubfuture.desafioPubfuture.service;

import desafioPubfuture.desafioPubfuture.exeptions.InexistenteException;
import desafioPubfuture.desafioPubfuture.model.Conta;
import desafioPubfuture.desafioPubfuture.model.Despesa;
import desafioPubfuture.desafioPubfuture.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DespesaService {

    private final DespesaRepository despesaRepository;
    private final ContaService contaService;

    @Autowired
    public DespesaService(DespesaRepository despesaRepository, ContaService contaService) {
        this.despesaRepository = despesaRepository;
        this.contaService = contaService;
    }

    public void addDespesa(Despesa despesa){
        Conta conta = contaService.getConta(despesa.getConta().getId());
        conta.setSaldo(conta.getSaldo() - despesa.getValor());
        contaService.saveConta(conta);
        despesaRepository.save(despesa);
    }

    public Despesa getDespesa(Long id) {
        return despesaRepository.findById(id).orElseThrow(() -> new InexistenteException("Despesa " + id + " inexistente"));
    }

    public void editarDespesa(Long id, Despesa novaDespesa) {
        Despesa despesaToUpdate = getDespesa(id);

        //conferindo se a conta e a possivel nova conta existem
        Conta conta = contaService.getConta(despesaToUpdate.getConta().getId());
        Conta possivelNovaConta = contaService.getConta(novaDespesa.getConta().getId());

        //retirando o valor antes de fazer update na conta
        conta.setSaldo(conta.getSaldo() + despesaToUpdate.getValor());
        contaService.saveConta(conta);

        //demais updates
        despesaToUpdate.setValor(novaDespesa.getValor());
        despesaToUpdate.setDataPagamento(novaDespesa.getDataPagamento());
        despesaToUpdate.setDataPagamentoEsperado(novaDespesa.getDataPagamentoEsperado());
        despesaToUpdate.setTipoDespesa(novaDespesa.getTipoDespesa());

        //inserindo agora a possivel novaDespesa em uma possivelNovaConta
        despesaToUpdate.setConta(possivelNovaConta);
        possivelNovaConta.setSaldo(possivelNovaConta.getSaldo() - novaDespesa.getValor());

        contaService.saveConta(possivelNovaConta);
        despesaRepository.save(despesaToUpdate);
    }

    //estarei tratando deletes apenas como remoção da lista, e não como refunds
    //refunds apenas ao editarDespesa
    public void deleteDespesa(Long id) {
        getDespesa(id);
        despesaRepository.deleteById(id);
    }

    public List<Despesa> getAllDespesas() {
        return despesaRepository.findAll();
    }

    public List<Despesa> getDespesaByPeriodo(Date dataInicio, Date dataFim) {
        return despesaRepository.findAllByDataPagamentoBetween(dataInicio, dataFim);
    }

    public List<Despesa> getDespesaByTipo(String tipoDespesa) {
        return despesaRepository.findAllByTipoDespesa(tipoDespesa);
    }

    public Double getValorTotal() {
        return despesaRepository.getValorTotal();
    }

}

