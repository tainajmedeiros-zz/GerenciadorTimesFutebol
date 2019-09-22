package br.com.gerenciador.models;

import java.time.LocalDate;

public class Time implements Comparable<Time>  {

    private Long id;
    private String nome;
    private LocalDate dataCriacao;
    private String corUniformePrincipal;
    private String corUniformeSecundario;
    private Long capitao = null;

    public Time(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        this.id = id;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.corUniformePrincipal = corUniformePrincipal;
        this.corUniformeSecundario = corUniformeSecundario;
    }

    public void definirCapitao(Long idJogador) {
        this.capitao = idJogador;
    }

    public int compareTo(Time time){
        if(time.getId() > this.id){
            return 1;
        }else{
            return 0;
        }
    }


    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCorUniformePrincipal() {
        return corUniformePrincipal;
    }

    public String getCorUniformeSecundario() {
        return corUniformeSecundario;
    }

    public Long getCapitao() {
        return capitao;
    }
}