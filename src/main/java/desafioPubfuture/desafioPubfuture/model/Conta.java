package desafioPubfuture.desafioPubfuture.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("nome")
    @NotBlank(message = "Nome não informado.")
    private String nome;

    private Double saldo;

    @JsonProperty("tipoConta")
    @Min(value = 1, message = "Insira '1' para Conta Carteira. Insira '2' para Conta Corrente. Insira '3' para Conta Poupança.")
    @Max(value = 3, message = "Insira '1' para Conta Carteira. Insira '2' para Conta Corrente. Insira '3' para Conta Poupança.")
    private int tipoConta;

    @JsonProperty("instituicaoFinanceira")
    @NotBlank(message = "Instituição Financeira não informada.")
    private String instituicaoFinanceira;

    public Conta(String nome, Double saldo, int tipoConta, String instituicaoFinanceira) {
        this.nome = nome;
        this.saldo = saldo;
        this.tipoConta = tipoConta;
        this.instituicaoFinanceira = instituicaoFinanceira;
    }

    public Conta() {

    }

    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }

    public Double getSaldo() {
        return saldo;
    }

    public int getTipoConta() {
        return tipoConta;
    }

    public String getInstituicaoFinanceira() {
        return instituicaoFinanceira;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipoConta(int tipoConta) {
        this.tipoConta = tipoConta;
    }

    public void setInstituicaoFinanceira(String instituicaoFinanceira){
        this.instituicaoFinanceira = instituicaoFinanceira;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

}