package desafioPubfuture.desafioPubfuture.service;

import desafioPubfuture.desafioPubfuture.exeptions.InexistenteException;
import desafioPubfuture.desafioPubfuture.model.Conta;
import desafioPubfuture.desafioPubfuture.repository.ContaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ContaServiceTest {

    @InjectMocks
    private ContaService contaService;
    @Mock
    private ContaRepository contaRepository;

    @Test
    public void testAddConta() {
        contaService.addConta(new Conta());
        Mockito.verify(contaRepository).save(Mockito.any());
    }

    @Test(expected = InexistenteException.class)
    public void testEditarContaNaoExistente() {
        Mockito.when(contaRepository.findById(1L)).thenReturn(Optional.empty());

        Conta contaParaSalvar = new Conta();
        contaService.editarConta(1L, contaParaSalvar);

        Mockito.verify(contaRepository,Mockito.never()).save(Mockito.any());
    }

    @Test
    public void testEditarConta() {
        Conta contaSalva = Mockito.mock(Conta.class);
        Mockito.when(contaRepository.findById(1L)).thenReturn(Optional.of(contaSalva));

        Conta contaParaSalvar = new Conta();
        contaParaSalvar.setNome("conta alterada");
        contaParaSalvar.setTipoConta(1);
        contaParaSalvar.setInstituicaoFinanceira("Itau");
        contaService.editarConta(1L, contaParaSalvar);

        Mockito.verify(contaRepository).save(contaSalva);
        Mockito.verify(contaSalva).setNome(contaParaSalvar.getNome());
        Mockito.verify(contaSalva).setTipoConta(contaParaSalvar.getTipoConta());
        Mockito.verify(contaSalva).setInstituicaoFinanceira(contaParaSalvar.getInstituicaoFinanceira());
        Mockito.verify(contaSalva, Mockito.never()).setSaldo(Mockito.any());
    }

    @Test
    public void testDeleteConta() {
        Conta contaSalva = Mockito.mock(Conta.class);
        Mockito.when(contaRepository.findById(1L)).thenReturn(Optional.of(contaSalva));

        contaService.deleteConta(1L);

        Mockito.verify(contaRepository).deleteById(1L);
    }

    @Test
    public  void testTransfereSaldo() {
        Conta origem = Mockito.mock(Conta.class);
        Conta destino = Mockito.mock(Conta.class);
        Mockito.when(contaRepository.findById(1L)).thenReturn(Optional.of(origem));
        Mockito.when(contaRepository.findById(2L)).thenReturn(Optional.of(destino));
        Mockito.when(origem.getSaldo()).thenReturn(100D);
        Mockito.when(destino.getSaldo()).thenReturn(100D);

        contaService.transfereSaldo(1L, 20D, 2L);

        Mockito.verify(origem).setSaldo(80D);
        Mockito.verify(contaRepository).save(origem);
        Mockito.verify(destino).setSaldo(120D);
        Mockito.verify(contaRepository).save(destino);
    }
}