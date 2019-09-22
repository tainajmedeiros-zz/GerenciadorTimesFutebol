package test.br.com.gerenciador;

import br.com.gerenciador.DesafioMeuTimeApplication;
import br.com.gerenciador.exceptions.IdentificadorUtilizadoException;
import br.com.gerenciador.exceptions.TimeNaoEncontradoException;
import org.junit.Before;
import org.junit.Test;

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
        assertEquals(1, desafio.times.size());
    }

    @Test(expected = IdentificadorUtilizadoException.class)
    public void incluirTimeComMesmoId(){
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
    }

}
