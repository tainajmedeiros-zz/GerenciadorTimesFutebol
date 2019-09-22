package br.com.gerenciador;
import br.com.gerenciador.models.Jogador;
import br.com.gerenciador.models.Time;
import br.com.gerenciador.exceptions.TimeNaoEncontradoException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DesafioMeuTimeApplication implements MeuTimeInterface{

    public List<Jogador> jogadores = new ArrayList<>();
    public List<Time> times = new ArrayList<>();

    @Override
    public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        verificarExistenciaIdTime(id);
        times.add(new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
    }

    @Override
    public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario)  {
        verificarExistenciaIdJogador(id);
        verificarExistenciaTime(idTime);
        jogadores.add(new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
    }

    @Override
    public void definirCapitao(Long idJogador) {

    }

    @Override
    public Long buscarCapitaoDoTime(Long idTime) {
        return null;
    }

    @Override
    public String buscarNomeJogador(Long idJogador) {
        return null;
    }

    @Override
    public String buscarNomeTime(Long idTime) {
        return null;
    }

    @Override
    public Long buscarJogadorMaiorSalario(Long idTime) {
        return null;
    }

    @Override
    public BigDecimal buscarSalarioDoJogaodor(Long idJogador) {
        return null;
    }

    @Override
    public List<Long> buscarJogadoresDoTime(Long idTime) {
        return null;
    }

    @Override
    public Long buscarMelhorJogadorDoTime(Long idTime) {
        return null;
    }

    @Override
    public Long buscarJogadorMaisVelho(Long idTime) {
        return null;
    }

    @Override
    public List<Long> buscarTimes() {
        return null;
    }

    @Override
    public List<Long> buscarTopJogadores(Integer top) {
        return null;
    }


    public void verificarExistenciaTime(Long idTime) throws TimeNaoEncontradoException {
        boolean existeTime = false;

        for (Time t : times) {
            if (t.getId() == idTime) {
                existeTime = true;
                break;
            }
        }
         if(existeTime) throw new br.com.gerenciador.exceptions.TimeNaoEncontradoException();
    }

    public void verificarExistenciaIdTime(Long idTime) {
        boolean existeId = false;

        for (Time t : times) {
            if (t.getId() == idTime) {
                existeId = true;
                break;
            }
        }
        if(existeId) throw new br.com.gerenciador.exceptions.IdentificadorUtilizadoException();
    }

    public void verificarExistenciaIdJogador(Long id) {
        boolean existeId = false;

        for (Jogador j : jogadores) {
            if (j.getId() == id) {
                existeId = true;
                break;
            }
        }
        if(existeId) throw new br.com.gerenciador.exceptions.IdentificadorUtilizadoException();
    }


}
