package br.com.gerenciador.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class Jogador implements Comparable<Jogador> {

    private Long id;
    private Long idTime;
    private String nome;
    private LocalDate dataNascimento;
    private Integer nivelHabilidade;
    private BigDecimal salario;

    public Jogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
        this.id = id;
        this.idTime = idTime;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        setNivelHabilidade(nivelHabilidade);
        this.salario = salario;
    }

    public int calcularIdade(){
        return Period.between(getDataNascimento(), LocalDate.now()).getYears();
    }

    public int compareTo(Jogador jogador){
        if(jogador.getNivelHabilidade() > this.nivelHabilidade){
            return 1;
        }else{
            return 0;
        }
    }

    public void setNivelHabilidade(Integer nivelHabilidade) {
        if(nivelHabilidade < 0){
            this.nivelHabilidade = 0;
        }else if(nivelHabilidade > 100){
            this.nivelHabilidade = 100;
        }else
            this.nivelHabilidade = nivelHabilidade;
    }
    public Long getId() {
        return id;
    }

    public Long getIdTime() {
        return idTime;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public int getNivelHabilidade() {
        return nivelHabilidade;
    }

    public BigDecimal getSalario() {
        return salario;
    }

}