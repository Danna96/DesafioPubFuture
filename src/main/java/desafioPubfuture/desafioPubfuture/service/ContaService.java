
package desafioPubfuture.desafioPubfuture.service;

import desafioPubfuture.desafioPubfuture.exeptions.InexistenteException;
import desafioPubfuture.desafioPubfuture.model.Conta;
import desafioPubfuture.desafioPubfuture.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {

    private final ContaRepository contaRepository;

    @Autowired
    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public void addConta(Conta conta) {
        contaRepository.save(conta);
    }

    public void editarConta(Long id, Conta conta) {
        Conta contaToUpdate = getConta(id);
        contaToUpdate.setNome(conta.getNome());
        contaToUpdate.setTipoConta(conta.getTipoConta());
        contaToUpdate.setInstituicaoFinanceira(conta.getInstituicaoFinanceira());
        contaRepository.save(contaToUpdate);
    }

    public void deleteConta(Long id) {
        getConta(id);
        contaRepository.deleteById(id);
    }

    public List<Conta> getAllContas() {
        return contaRepository.findAll();
    }

    public void transfereSaldo(Long id, Double valor, Long idDestino) {
        Conta origem = getConta(id);
        Conta destino = getConta(idDestino);

        origem.setSaldo(origem.getSaldo() - valor);
        contaRepository.save(origem);
        destino.setSaldo(destino.getSaldo() + valor);
        contaRepository.save(destino);
    }

    public Double getSaldoTotal() {
        return contaRepository.getSaldoTotal();
    }

    public Conta getConta(Long id) {
        return contaRepository.findById(id).orElseThrow(() -> new InexistenteException("Conta " + id + " inexistente."));
    }

    public void saveConta(Conta conta) {
        contaRepository.save(conta);
    }

}

