package desafioPubfuture.desafioPubfuture.controller;

import desafioPubfuture.desafioPubfuture.model.Despesa;
import desafioPubfuture.desafioPubfuture.service.DespesaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Tag(name = "Controlador de despesas.")
@RequestMapping("api/despesa")
@RestController
public class DespesaController {

    private final DespesaService despesaService;

    @Autowired
    public DespesaController(DespesaService despesaService) {
        this.despesaService = despesaService;
    }

    @Operation(summary = "Adicionar despesa.")
    @PostMapping
    public void addDespesa(@Valid @RequestBody Despesa despesa) {
        despesaService.addDespesa(despesa);
    }

    @Operation(summary = "Buscar despesa.")
    @GetMapping(path = "{id}")
    public Despesa getDespesa(@PathVariable("id") Long id) {
        return despesaService.getDespesa(id);
    }

    @Operation(summary = "Editar despesa.")
    @PutMapping(path = "/{id}")
    public void editarDespesa(@PathVariable("id") Long id, @Valid @RequestBody Despesa despesa) {
        despesaService.editarDespesa(id, despesa);
    }

    @Operation(summary = "Apagar despesa.")
    @DeleteMapping(path = "{id}")
    public void deleteDespesa(@PathVariable("id") Long id) {
        despesaService.deleteDespesa(id);
    }

    @Operation(summary = "Buscar todas as despesas.")
    @GetMapping
    public List<Despesa> getAllDespesas() {
        return despesaService.getAllDespesas();
    }

    @Operation(summary = "Filtrar despesas por período.")
    @GetMapping(path = "/filtroPeriodo")
    public List<Despesa> getDespesaByPeriodo (@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dataInicio,
                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dataFim) {
        return despesaService.getDespesaByPeriodo(dataInicio, dataFim);
    }

    @Operation(summary = "Filtrar despesas pelo tipo.")
    @GetMapping(path = "/filtroTipo")
    public List<Despesa> getDespesaByTipo(@RequestParam String tipoDespesa) {
        return despesaService.getDespesaByTipo(tipoDespesa);
    }

    @Operation(summary = "Buscar valor total de todas as despesas.")
    @GetMapping(path = "/valorTotal")
    public double getValorTotal() {
        return despesaService.getValorTotal();
    }

}
