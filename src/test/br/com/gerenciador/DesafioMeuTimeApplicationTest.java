package test.br.com.gerenciador;

import br.com.gerenciador.DesafioMeuTimeApplication;
import br.com.gerenciador.exceptions.CapitaoNaoInformadoException;
import br.com.gerenciador.exceptions.IdentificadorUtilizadoException;
import br.com.gerenciador.exceptions.JogadorNaoEncontradoException;
import br.com.gerenciador.exceptions.TimeNaoEncontradoException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class DesafioMeuTimeApplicationTest {

    private DesafioMeuTimeApplication desafio;

    @Before
    public void setUp() {
        desafio = new DesafioMeuTimeApplication();
    }

    @Test
    public void incluirTimeComSucesso(){
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
        desafio.incluirTime(2L,"flamengo", LocalDate.of(1988,06,18),"vermelho", "preto");
        assertEquals(2, desafio.times.size());
    }

    @Test(expected = IdentificadorUtilizadoException.class)
    public void incluirTimeComMesmoId(){
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
    }

    @Test
    public void incluirJogadorComSucesso(){
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
        desafio.incluirJogador(1L,1L,"Taina",LocalDate.of(1998,06,18),10, BigDecimal.valueOf(10));
        desafio.incluirJogador(2L,1L,"Hand",LocalDate.of(1998,06,18),9, BigDecimal.valueOf(30));
        assertEquals(2,desafio.jogadores.size());
    }

    @Test(expected = IdentificadorUtilizadoException.class)
    public void incluirJogadorComMesmoId(){
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
        desafio.incluirJogador(1L,1L,"Taina",LocalDate.of(1998,06,18),10, BigDecimal.valueOf(10));
        desafio.incluirJogador(1L,1L,"Hand",LocalDate.of(1998,06,18),9, BigDecimal.valueOf(30));
    }

    @Test
    public void definirCapitaoComSucesso(){
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
        desafio.incluirJogador(1L,1L,"Taina",LocalDate.of(1998,06,18),10, BigDecimal.valueOf(10));
        desafio.definirCapitao(1L);
    }

    @Test(expected = JogadorNaoEncontradoException.class)
    public void definirCapitaoSemJogadorExistir(){
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
        desafio.incluirJogador(1L,1L,"Taina",LocalDate.of(1998,06,18),10, BigDecimal.valueOf(10));
        desafio.definirCapitao(2L);
    }

    @Test
    public void buscarCapitaoComSucesso(){
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
        desafio.incluirJogador(1L,1L,"Taina",LocalDate.of(1998,06,18),10, BigDecimal.valueOf(10));
        desafio.definirCapitao(1L);
        assertEquals(Long.valueOf(1L), desafio.buscarCapitaoDoTime(1L));

    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void buscarCapitaoDeTimeQueNaoExiste(){
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
        desafio.incluirJogador(1L,1L,"Taina",LocalDate.of(1998,06,18),10, BigDecimal.valueOf(10));
        desafio.buscarCapitaoDoTime(2L);
    }

    @Test(expected = CapitaoNaoInformadoException.class)
    public void buscarCapitaoDeTimeQueNaoExisteCapitao(){
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
        desafio.incluirJogador(1L,1L,"Taina",LocalDate.of(1998,06,18),10, BigDecimal.valueOf(10));
        desafio.buscarCapitaoDoTime(1L);
    }

    @Test
    public void buscarNomeJogadorComSucesso(){
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
        desafio.incluirJogador(1L,1L,"Taina",LocalDate.of(1998,06,18),10, BigDecimal.valueOf(10));
        assertEquals("Taina", desafio.buscarNomeJogador(1L));
    }

    @Test(expected = JogadorNaoEncontradoException.class)
    public void buscarNomeJogadorQueNaoExiste(){
        desafio.buscarNomeJogador(5L);
    }

    @Test
    public void buscarNomeTimeComSucesso(){
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
        assertEquals("vasco", desafio.buscarNomeTime(1L));
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void buscarNomeTimeQueNaoExiste(){
        desafio.buscarNomeTime(10L);
    }

    @Test
    public void buscarJogadorComMaiorSalarioComSucesso(){
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
        desafio.incluirJogador(1L,1L,"Taina",LocalDate.of(1998,06,18),10, BigDecimal.valueOf(10));
        desafio.incluirJogador(2L,1L,"Hand",LocalDate.of(1998,06,18),9, BigDecimal.valueOf(30));
        assertEquals(Long.valueOf(2L),desafio.buscarJogadorMaiorSalario(1L));
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void buscarJogadorComMaiorSalarioSemTime(){
        desafio.buscarJogadorMaiorSalario(1L);
    }

    @Test
    public void buscarSalarioDoJogadorComSucesso(){
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
        desafio.incluirJogador(1L,1L,"Taina",LocalDate.of(1998,06,18),10, BigDecimal.valueOf(10));
        desafio.incluirJogador(2L,1L,"Hand",LocalDate.of(1998,06,18),9, BigDecimal.valueOf(30));
        assertEquals(BigDecimal.valueOf(10),desafio.buscarSalarioDoJogador(1L));
    }

    @Test(expected = JogadorNaoEncontradoException.class)
    public void buscarSalarioDoJogadorQueNaoExiste(){
        desafio.buscarSalarioDoJogador(56L);
    }

    @Test
    public void buscarJogadoresDoTimeComSucesso(){
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
        desafio.incluirJogador(2L,1L,"Taina",LocalDate.of(1998,06,18),10, BigDecimal.valueOf(10));
        desafio.incluirJogador(3L,1L,"Hand",LocalDate.of(1998,06,18),9, BigDecimal.valueOf(30));
        desafio.incluirJogador(1L,1L,"Stella",LocalDate.of(1998,06,18),9, BigDecimal.valueOf(30));
        List<Long> lista = desafio.buscarJogadoresDoTime(1L);
        assertEquals(lista.get(0), Long.valueOf(1L));
        assertEquals(lista.get(1), Long.valueOf(2L));
        assertEquals(lista.get(2), Long.valueOf(3L));
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void buscarJogadoresDoTimeQueNaoExisteTime(){
        desafio.buscarJogadoresDoTime(8L);
    }

    @Test
    public void buscarMelhorJogadorDoTime(){
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
        desafio.incluirJogador(2L,1L,"Taina",LocalDate.of(1998,06,18),10, BigDecimal.valueOf(10));
        desafio.incluirJogador(3L,1L,"Hand",LocalDate.of(1998,06,18),9, BigDecimal.valueOf(30));
        desafio.incluirJogador(1L,1L,"Stella",LocalDate.of(1998,06,18),9, BigDecimal.valueOf(30));
        assertEquals(Long.valueOf(2L), desafio.buscarMelhorJogadorDoTime(1L));
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void buscarMelhorJogadorDoTimeQueNaoExisteTime(){
        desafio.buscarMelhorJogadorDoTime(7L);
    }
}
