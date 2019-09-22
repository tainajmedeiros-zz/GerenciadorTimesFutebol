package br.com.gerenciador;
import br.com.gerenciador.exceptions.JogadorNaoEncontradoException;
import br.com.gerenciador.models.Jogador;
import br.com.gerenciador.models.Time;
import br.com.gerenciador.exceptions.TimeNaoEncontradoException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        if(!verificarExistenciaTime(idTime)) throw new br.com.gerenciador.exceptions.TimeNaoEncontradoException();

        for (Time t : times) {
            if (t.getId() == idTime) {
                return t.getNome();
            }
        }
        return null;    }

    @Override
    public Long buscarJogadorMaiorSalario(Long idTime) {
        if(!verificarExistenciaTime(idTime)) throw new br.com.gerenciador.exceptions.TimeNaoEncontradoException();

        BigDecimal maoirSalario = new BigDecimal(0);
        Long idJogadorMaiorSalario = null;

        for (Jogador j : jogadores) {
            if (j.getIdTime() == idTime) {
                if (j.getSalario().doubleValue() > maoirSalario.doubleValue()) {
                    idJogadorMaiorSalario = j.getId();
                }
            }
        }

        return idJogadorMaiorSalario;
    }

    @Override
    public BigDecimal buscarSalarioDoJogador(Long idJogador) {
        if(!verificarExistenciaJogador(idJogador)) throw new br.com.gerenciador.exceptions.JogadorNaoEncontradoException();

        for (Jogador j : jogadores) {
            if (j.getId() == idJogador) {
                return j.getSalario();
            }
        }
        return null;
    }

    @Override
    public List<Long> buscarJogadoresDoTime(Long idTime) {

        if(!verificarExistenciaTime(idTime)) throw new br.com.gerenciador.exceptions.TimeNaoEncontradoException();

        List<Long> jogadoresTime = new ArrayList<>();

        for (Jogador j : jogadores) {
            if (j.getIdTime() == idTime) {
                jogadoresTime.add(j.getId());
            }
        }

        Collections.sort(jogadoresTime);

        return jogadoresTime;
    }

    @Override
    public Long buscarMelhorJogadorDoTime(Long idTime) {

        if(!verificarExistenciaTime(idTime)) throw new br.com.gerenciador.exceptions.TimeNaoEncontradoException();

        Integer nivelMaiorHabilidade = 0;
        Long idMelhorJogador = null;

        for (Jogador j : jogadores) {
            if (j.getIdTime() == idTime) {
                if (j.getNivelHabilidade() > nivelMaiorHabilidade) {
                    nivelMaiorHabilidade = j.getNivelHabilidade();
                    idMelhorJogador = j.getId();
                }
            }
        }
        return idMelhorJogador;
    }

    @Override
    public Long buscarJogadorMaisVelho(Long idTime) {
        if(!verificarExistenciaTime(idTime)) throw new br.com.gerenciador.exceptions.TimeNaoEncontradoException();

        Integer maoirIdade = 0;
        Long idJogadorMaisVelho = null;

        for (Jogador j : jogadores) {
            if (j.getIdTime() == idTime && j.calcularIdade() >= maoirIdade) {
                maoirIdade = j.calcularIdade();
                if (idJogadorMaisVelho == null || j.getId() < idJogadorMaisVelho) {
                        idJogadorMaisVelho = j.getId();
                }
            }
        }
        return idJogadorMaisVelho;
    }

    @Override
    public List<Long> buscarTimes() {
        List<Long> listaTimes = new ArrayList<>();

        for (Time t : times) {
            listaTimes.add(t.getId());
        }

        Collections.sort(listaTimes);

        return listaTimes;
    }

    @Override
    public List<Long> buscarTopJogadores(Integer top) {
        List<Jogador> listaAuxiliar = new ArrayList<>();
        List<Long> topJogadores = new ArrayList<>();

        listaAuxiliar.addAll(jogadores);
        listaAuxiliar.sort(Comparator.comparing(Jogador::getNivelHabilidade, Comparator.reverseOrder()));

        for (int i = 0; i < top; i++) {
            topJogadores.add(listaAuxiliar.get(i).getId());
        }

        return topJogadores;
    }

    @Override
    public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {

        String corTimeCasa = "";
        String corTimeFora = "";

        for(Time t: times){
            if(t.getId() == timeDaCasa){
                corTimeCasa = t.getCorUniformePrincipal();
            }else if(t.getId() == timeDeFora && !t.getCorUniformePrincipal().equals(corTimeCasa)){
                corTimeFora = t.getCorUniformePrincipal();
            }else
                corTimeFora = t.getCorUniformeSecundario();
        }

        return corTimeFora;    }


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
