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
        this.nivelHabilidade = nivelHabilidade;
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

    public Integer getNivelHabilidade() {
        return nivelHabilidade;
    }

    public BigDecimal getSalario() {
        return salario;
    }

}