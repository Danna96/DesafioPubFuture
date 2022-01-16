
package desafioPubfuture.desafioPubfuture.controller;

import desafioPubfuture.desafioPubfuture.model.Conta;
import desafioPubfuture.desafioPubfuture.service.ContaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Controlador de contas.")
@RequestMapping("api/conta")
@RestController
public class ContaController {

    private final ContaService contaService;

    @Autowired
    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @Operation(summary = "Adicionar nova conta.")
    @PostMapping
    public void addConta(@Valid @RequestBody Conta conta) {
        contaService.addConta(conta);
    }

    @Operation(summary = "Buscar conta.")
    @GetMapping(path = "{id}")
    public Conta getConta(@PathVariable("id") Long id) {
        return contaService.getConta(id);
    }

    @Operation(summary = "Editar conta.")
    @PutMapping(path = "/{id}")
    public void editarConta(@PathVariable("id") Long id, @RequestBody Conta conta) {
        contaService.editarConta(id, conta);
    }

    @Operation(summary = "Apagar conta.")
    @DeleteMapping(path = "{id}")
    public void deleteConta (@PathVariable("id") Long id) {
        contaService.deleteConta(id);
    }

    @Operation(summary = "Buscar todas as contas.")
    @GetMapping
    public List<Conta> getAllContas() {
        return contaService.getAllContas();
    }

    @Operation(summary = "Transferir de saldo entre contas.")
    @PutMapping(path = "/{id}/transfere")
    public void transfereSaldo(@PathVariable("id") Long id, @RequestParam Double valor, @RequestParam Long idDestino) {
        contaService.transfereSaldo(id, valor, idDestino);
    }

    @Operation(summary = "Buscar saldo total de todas as contas.")
    @GetMapping(path = "/saldoTotal")
    public Double getSaldoTotal() {
        return contaService.getSaldoTotal();
    }

}

