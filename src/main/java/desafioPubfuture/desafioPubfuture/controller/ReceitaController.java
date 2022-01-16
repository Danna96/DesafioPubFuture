package desafioPubfuture.desafioPubfuture.controller;

import desafioPubfuture.desafioPubfuture.model.Receita;
import desafioPubfuture.desafioPubfuture.service.ReceitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Tag(name = "Controlador de receitas.")
@RequestMapping("api/receita")
@RestController
public class ReceitaController {

    private final ReceitaService receitaService;

    @Autowired
    public ReceitaController(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @Operation(summary = "Adicinar receita.")
    @PostMapping
    public void addReceita(@Valid @RequestBody Receita receita) {
        receitaService.addReceita(receita);
    }

    @Operation(summary = "Buscar receita.")
    @GetMapping(path = "{id}")
    public Receita getReceita(@PathVariable("id") Long id) {
        return receitaService.getReceita(id);
    }

    @Operation(summary = "Editar receita.")
    @PutMapping(path = "/{id}")
    public void editarReceita( @PathVariable("id") Long id, @Valid @RequestBody Receita receita) {
        receitaService.editarReceita(id, receita);
    }

    @Operation(summary = "Apagar receita.")
    @DeleteMapping(path = "{id}")
    public void deleteReceita(@PathVariable("id") Long id) {
        receitaService.deleteReceita(id);
    }

    @Operation(summary = "Buscar todas as receitas.")
    @GetMapping
    public List<Receita> getAllReceitas() {
        return receitaService.getAllReceitas();
    }

    @Operation(summary = "Filtrar receitas por período.")
    @GetMapping(path = "/filtroPeriodo")
    public List<Receita> getReceitaByPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dataInicio,
                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dataFim) {
        return receitaService.getReceitaByPeriodo(dataInicio, dataFim);
    }

    @Operation(summary = "Filtrar receitas por tipo.")
    @GetMapping(path = "/filtroTipo")
    public List<Receita> getReceitaByTipo(@RequestParam String tipoReceita) {
        return receitaService.getReceitaByTipo(tipoReceita);
    }

    @Operation(summary = "Buscar valor total de receitas.")
    @GetMapping(path = "/valorTotal")
    public Double getValorTotal() {
        return receitaService.getValorTotal();
    }

}
