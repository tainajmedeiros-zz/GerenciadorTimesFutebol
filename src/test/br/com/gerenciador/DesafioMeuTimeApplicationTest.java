package test.br.com.gerenciador;

import br.com.gerenciador.DesafioMeuTimeApplication;
import br.com.gerenciador.exceptions.IdentificadorUtilizadoException;
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
}
