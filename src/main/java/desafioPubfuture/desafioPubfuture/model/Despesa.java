package desafioPubfuture.desafioPubfuture.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("valor")
    @NotNull(message = "Informe o valor da despesa.")
    private Double valor;

    @JsonProperty("dataPagamento")
    @NotNull(message = "Informe a data do pagamento.")
    private Date dataPagamento;

    private Date dataPagamentoEsperado;

    @JsonProperty("tipoDespesa")
    @NotEmpty(message = "Informe o tipo de despesa. (Ex: alimentação, educação, lazer, moradia)")
    private String tipoDespesa;

    @ManyToOne()
    @JoinColumn(name = "contaId")
    @NotNull(message = "Informe a conta.")
    private Conta conta;

    public Despesa(Double valor,
                   Date dataPagamento,
                   Date dataPagamentoEsperado,
                   String tipoDespesa,
                   Conta conta) {

        this.valor = valor;
        this.dataPagamento = dataPagamento;
        this.dataPagamentoEsperado = dataPagamentoEsperado;
        this.tipoDespesa = tipoDespesa;
        this.conta = conta;
    }

    public Despesa() {

    }

    public Long getId() {
        return id;
    }

    public Double getValor() {
        return valor;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public Date getDataPagamentoEsperado() {
        return dataPagamentoEsperado;
    }

    public String getTipoDespesa() {
        return tipoDespesa;
    }

    public Conta getConta() {
        return conta;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public void setDataPagamentoEsperado(Date dataPagamentoEsperado) {
        this.dataPagamentoEsperado = dataPagamentoEsperado;
    }

    public void setTipoDespesa(String tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

}
