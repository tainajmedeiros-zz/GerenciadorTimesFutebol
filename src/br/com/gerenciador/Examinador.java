package br.com.gerenciador;

import br.com.gerenciador.models.Jogador;
import br.com.gerenciador.models.Time;

import java.util.List;

public class Examinador {

    public static Time acharTime(Long idTime, List<Time> times){

        for(Time t: times){
            if(t.getId() == idTime){
                return t;
            }
        }
        return null;
    }

    public static Jogador acharJogador(Long idJogador, List<Jogador> jogadores){

        for (Jogador j : jogadores) {
            if (j.getId() == idJogador) {
                return j;
            }
        }
        return null;
    }

}
