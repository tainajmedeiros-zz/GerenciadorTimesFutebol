package br.com.gerenciador;
import br.com.gerenciador.exceptions.JogadorNaoEncontradoException;
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
        if(verificarExistenciaTime(id)) throw new br.com.gerenciador.exceptions.IdentificadorUtilizadoException() ;
        times.add(new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
    }

    @Override
    public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario)  {
        if(verificarExistenciaJogador(id)) throw new br.com.gerenciador.exceptions.IdentificadorUtilizadoException();
        if(!verificarExistenciaTime(idTime)) throw new br.com.gerenciador.exceptions.TimeNaoEncontradoException();
        jogadores.add(new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
    }

    @Override
    public void definirCapitao(Long idJogador) {
        if(!verificarExistenciaJogador(idJogador)) throw new br.com.gerenciador.exceptions.JogadorNaoEncontradoException();

        Long timeJogador = null;

        for (Jogador j : jogadores) {
            if (j.getId() == idJogador) {
                timeJogador = j.getIdTime();
                break;
            }
        }

        for (Time t : times) {
            if (t.getId() == timeJogador) {
                t.definirCapitao(idJogador);
                break;
            }
        }
    }

    @Override
    public Long buscarCapitaoDoTime(Long idTime) {
        if(!verificarExistenciaTime(idTime)) throw new br.com.gerenciador.exceptions.TimeNaoEncontradoException();

        Long idCapitao = null;

        for (Time t : times) {
            if (t.getId() == idTime) {
                idCapitao = t.getCapitao();
                if (idCapitao != null) {
                    return idCapitao;
                }
                break;
            }
        }

        throw new br.com.gerenciador.exceptions.CapitaoNaoInformadoException();
    }

    @Override
    public String buscarNomeJogador(Long idJogador) {
        if(!verificarExistenciaJogador(idJogador)) throw new br.com.gerenciador.exceptions.JogadorNaoEncontradoException();

        for (Jogador j : jogadores) {
            if (j.getId() == idJogador) {
                return j.getNome();
            }
        }
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


    public boolean verificarExistenciaTime(Long idTime) throws TimeNaoEncontradoException {
        boolean existeTime = false;

        for (Time t : times) {
            if (t.getId() == idTime) {
                existeTime = true;
                break;
            }
        }
         return existeTime;
    }

    public boolean verificarExistenciaJogador(Long id) {
        boolean existeId = false;

        for (Jogador j : jogadores) {
            if (j.getId() == id) {
                existeId = true;
                break;
            }
        }
        return existeId;

    }


}
