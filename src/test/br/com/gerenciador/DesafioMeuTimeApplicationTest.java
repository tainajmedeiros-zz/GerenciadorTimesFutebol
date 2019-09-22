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
}
