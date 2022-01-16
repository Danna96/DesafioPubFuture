package desafioPubfuture.desafioPubfuture.service;

import desafioPubfuture.desafioPubfuture.exeptions.InexistenteException;
import desafioPubfuture.desafioPubfuture.model.Conta;
import desafioPubfuture.desafioPubfuture.model.Receita;
import desafioPubfuture.desafioPubfuture.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReceitaService {

    private final ReceitaRepository receitaRepository;
    private final ContaService contaService;

    @Autowired
    public ReceitaService(ReceitaRepository receitaRepository, ContaService contaService) {
        this.receitaRepository = receitaRepository;
        this.contaService = contaService;
    }

    public void addReceita(Receita receita) {
        Conta conta = contaService.getConta(receita.getConta().getId());
        receitaRepository.save(receita);
        conta.setSaldo(conta.getSaldo() + receita.getValor());
        contaService.saveConta(conta);
    }

    public Receita getReceita(Long id) {
        return receitaRepository.findById(id).orElseThrow(() -> new InexistenteException("Receita " + id + " inexistente."));
    }

    public void editarReceita(Long id, Receita novaReceita) {
        Receita receitaToUpdate = getReceita(id);

        //conferindo se a conta e a possivel nova conta existem
        Conta conta = contaService.getConta(receitaToUpdate.getConta().getId());
        Conta possivelNovaConta = contaService.getConta(novaReceita.getConta().getId());

        //retirando o valor antes de fazer update na conta
        conta.setSaldo(conta.getSaldo() - receitaToUpdate.getValor());
        contaService.saveConta(conta);

        //demais updates
        receitaToUpdate.setValor(novaReceita.getValor());
        receitaToUpdate.setDataRecebimento(novaReceita.getDataRecebimento());
        receitaToUpdate.setDataRecebimentoEsperado(novaReceita.getDataRecebimentoEsperado());
        receitaToUpdate.setDescricao(novaReceita.getDescricao());
        receitaToUpdate.setTipoReceita(novaReceita.getTipoReceita());

        //inserindo agora a novaReceita em uma possivelNovaConta
        receitaToUpdate.setConta(possivelNovaConta);
        possivelNovaConta.setSaldo(possivelNovaConta.getSaldo() + novaReceita.getValor());

        contaService.saveConta(possivelNovaConta);
        receitaRepository.save(receitaToUpdate);
    }

    //estarei tratando deletes apenas como remoção da lista, e não como refunds
    //refunds apenas ao editarReceita
    public void deleteReceita(Long id) {
        getReceita(id);
        receitaRepository.deleteById(id);
    }

    public List<Receita> getAllReceitas() {
        return receitaRepository.findAll();
    }

    public List<Receita> getReceitaByPeriodo(Date dataInicio, Date dataFim) {
        return receitaRepository.findAllByDataRecebimentoBetween(dataInicio, dataFim);
    }

    public List<Receita> getReceitaByTipo(String tipoReceita) {
        return receitaRepository.findAllByTipoReceita(tipoReceita);
    }

    public Double getValorTotal() {
        return receitaRepository.getValorTotal();
    }

}
