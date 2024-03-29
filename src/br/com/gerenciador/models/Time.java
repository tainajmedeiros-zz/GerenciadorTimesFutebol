package br.com.gerenciador.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Time {

    private Long id;
    private String nome;
    private LocalDate dataCriacao;
    private String corUniformePrincipal;
    private String corUniformeSecundario;
    private Jogador capitao;
    private List<Jogador> jogadores;

    public Time(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        this.id = id;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.corUniformePrincipal = corUniformePrincipal;
        this.corUniformeSecundario = corUniformeSecundario;
        this.jogadores = new ArrayList<>();
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

    public Jogador getCapitao() {
        return capitao;
    }

    public void adicionarJogador(Jogador jogador) {
        jogadores.add(jogador);
    }

    public void definirCapitao(Jogador jogador) {
        this.capitao = jogador;
    }

    public List<Long> listarJogadores(){

        List<Long> jogadoresTime = new ArrayList<Long>();

        for (Jogador j : jogadores) {
            jogadoresTime.add(j.getId());
        }

        jogadoresTime.sort(Long::compareTo);
        return jogadoresTime;
    }

    public Long melhorJogadorDoTime(){
        Integer nivelMaiorHabilidade = 0;
        Jogador melhorJogador = null;

        for (Jogador j : jogadores) {
            if (j.getNivelHabilidade() > nivelMaiorHabilidade) {
                nivelMaiorHabilidade = j.getNivelHabilidade();
                melhorJogador = j;
            }
            else if(nivelMaiorHabilidade == j.getNivelHabilidade()){
                if(j.getId() < melhorJogador.getId()){
                    melhorJogador = j;
                }
            }
        }

        return melhorJogador.getId();
    }

    public Long jogadorMaisVelho(){
        Integer maoirIdade = 0;
        Jogador jogadorMaisVelho = null;
        Integer idade;

        for (Jogador j : jogadores) {
            idade = j.calcularIdade();
            if (idade > maoirIdade) {
                maoirIdade = idade;
                jogadorMaisVelho = j;
            }
            else if(idade == maoirIdade){
                if(j.getId() < jogadorMaisVelho.getId()){
                    jogadorMaisVelho = j;
                }
            }
        }
        return jogadorMaisVelho.getId();
    }

    public Long jogadorMaiorSalario(){

        BigDecimal maoirSalario = new BigDecimal(0);
        Jogador jogadorMaiorSalario = null;

        for (Jogador j : jogadores) {
            if(j.getSalario().doubleValue() > maoirSalario.doubleValue()){
                jogadorMaiorSalario = j;
                maoirSalario = j.getSalario();
            }
            else if(j.getSalario().doubleValue() == maoirSalario.doubleValue()){
                if(j.getId() < jogadorMaiorSalario.getId()){
                    jogadorMaiorSalario = j;

                }
            }
        }
        return jogadorMaiorSalario.getId();
    }

    public String definirCamisaPartida(String corTimeCasa){

        if(corUniformePrincipal.equals(corTimeCasa)) return corUniformeSecundario;
        return corUniformePrincipal;

    }

}