package desafioPubfuture.desafioPubfuture.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("valor")
    @NotNull(message = "Informe o valor da receita.")
    private Double valor;

    @JsonProperty("dataRecebimento")
    @NotNull(message = "Informe a data de recebimento.")
    private Date dataRecebimento;

    @JsonProperty("dataRecebimentoEsperado")
    private Date dataRecebimentoEsperado;
    private String descricao;

    @JsonProperty("tipoReceita")
    @NotBlank(message = "Informe o tipo de receita. (Ex: salário, presente, prêmio)")
    private String tipoReceita;

    @ManyToOne()
    @JoinColumn(name = "contaId")
    @NotNull(message = "Informe a conta.")
    private Conta conta;

    public Receita(Double valor,
                   Date dataRecebimento,
                   Date dataRecebimentoEsperado,
                   String descricao,
                   String tipoReceita,
                   Conta conta) {

        this.valor = valor;
        this.dataRecebimento = dataRecebimento;
        this.dataRecebimentoEsperado = dataRecebimentoEsperado;
        this.descricao = descricao;
        this.tipoReceita = tipoReceita;
        this.conta = conta;
    }

    public Receita() {

    }

    public Long getId() {
        return id;
    }

    public Double getValor() {
        return valor;
    }

    public Date getDataRecebimento() {
        return dataRecebimento;
    }

    public Date getDataRecebimentoEsperado() {
        return dataRecebimentoEsperado;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTipoReceita() {
        return tipoReceita;
    }

    public Conta getConta() {
        return conta;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setDataRecebimento(Date dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public void setDataRecebimentoEsperado(Date dataRecebimentoEsperado) {
        this.dataRecebimentoEsperado = dataRecebimentoEsperado;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setTipoReceita(String tipoReceita) {
        this.tipoReceita = tipoReceita;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

}
