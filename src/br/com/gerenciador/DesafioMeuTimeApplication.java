package br.com.gerenciador;
import br.com.gerenciador.exceptions.JogadorNaoEncontradoException;
import br.com.gerenciador.models.Jogador;
import br.com.gerenciador.models.Time;
import br.com.gerenciador.exceptions.TimeNaoEncontradoException;
import br.com.gerenciador.exceptions.IdentificadorUtilizadoException;
import br.com.gerenciador.exceptions.CapitaoNaoInformadoException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static br.com.gerenciador.Examinador.acharJogador;
import static br.com.gerenciador.Examinador.acharTime;


public class DesafioMeuTimeApplication implements MeuTimeInterface{

    public List<Jogador> jogadores = new ArrayList<>();
    public List<Time> times = new ArrayList<>();

    @Override
    public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        if(acharTime(id,this.times) != null) throw new IdentificadorUtilizadoException();
        times.add(new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
    }

    @Override
    public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario)  {
        if(acharJogador(id, this.jogadores) != null) throw new IdentificadorUtilizadoException();
        Time t = acharTime(idTime, times);
        if(t == null) throw new TimeNaoEncontradoException();
        Jogador jogador = new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);
        jogadores.add(jogador);
        t.adicionarJogador(jogador);
    }

    @Override
    public void definirCapitao(Long idJogador) {
        Jogador j = acharJogador(idJogador, jogadores);
        if (j == null) throw new JogadorNaoEncontradoException();
        Time t = acharTime(j.getIdTime(), times);
        if(t == null) throw new TimeNaoEncontradoException();
        t.definirCapitao(j); //verificar lista de jogadores na classe time
    }

    @Override
    public Long buscarCapitaoDoTime(Long idTime) {
        Time t = acharTime(idTime, times);
        if(t == null) throw new TimeNaoEncontradoException();
        if(t.getCapitao() == null) throw new CapitaoNaoInformadoException();
        return t.getCapitao().getId();
    }

    @Override
    public String buscarNomeJogador(Long idJogador) {
        Jogador j = acharJogador(idJogador, jogadores);
        if(j == null) throw new JogadorNaoEncontradoException();
        return j.getNome();
    }

    @Override
    public String buscarNomeTime(Long idTime) {
        Time t = acharTime(idTime, times);
        if(t == null) throw new TimeNaoEncontradoException();
        return t.getNome();
    }

    @Override
    public Long buscarJogadorMaiorSalario(Long idTime) {
        Time t = acharTime(idTime, times);
        if(t == null) throw new TimeNaoEncontradoException();
        return t.jogadorMaiorSalario();
    }

    @Override
    public BigDecimal buscarSalarioDoJogador(Long idJogador) {
        Jogador j = acharJogador(idJogador, jogadores);
        if(j == null) throw new JogadorNaoEncontradoException();
        return j.getSalario();
    }

    @Override
    public List<Long> buscarJogadoresDoTime(Long idTime) {
        Time t = acharTime(idTime, times);
        if(t == null) throw new TimeNaoEncontradoException();
        List<Long> jogadoresTime;
        jogadoresTime = t.listarJogadores();
        return jogadoresTime;
    }

    @Override
    public Long buscarMelhorJogadorDoTime(Long idTime) {
        Time t = acharTime(idTime, times);
        if(t == null) throw new TimeNaoEncontradoException();
        return t.melhorJogadorDoTime();
    }

    @Override
    public Long buscarJogadorMaisVelho(Long idTime) {
        Time t = acharTime(idTime, times);
        if(t == null) throw new TimeNaoEncontradoException();
        return t.jogadorMaisVelho();
    }

    @Override
    public List<Long> buscarTimes() {
        List<Long> listaTimes = new ArrayList<Long>();
        for (Time t : times) {
            listaTimes.add(t.getId());
        }
        listaTimes.sort(Long::compareTo);
        return listaTimes;
    }

    @Override
    public List<Long> buscarTopJogadores(Integer top) {
        List<Jogador> listaAuxiliar = new ArrayList<>();
        List<Long> topJogadores = new ArrayList<Long>();

        listaAuxiliar.addAll(jogadores);
        listaAuxiliar.sort(Comparator.comparing(Jogador::getNivelHabilidade, Comparator.reverseOrder()));

        for (int i = 0; i < top; i++) {
            topJogadores.add(listaAuxiliar.get(i).getId());
        }
        return topJogadores;
    }

    @Override
    public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
        Time tCasa = acharTime(timeDaCasa, times);
        Time tFora = acharTime(timeDeFora, times);
        if(tCasa == null || tFora == null) throw new TimeNaoEncontradoException();
        return tFora.definirCamisaPartida(tCasa.getCorUniformePrincipal());
    }





}
