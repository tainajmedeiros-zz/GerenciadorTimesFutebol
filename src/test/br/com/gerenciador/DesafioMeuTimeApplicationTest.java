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
import java.util.ArrayList;
import java.util.Arrays;
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
    public void incluirJogadorComNivelDeHabilidadeMenorZero(){
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
        desafio.incluirJogador(1L,1L,"Taina",LocalDate.of(1998,06,18),-10, BigDecimal.valueOf(10));
        assertEquals(0, desafio.jogadores.get(0).getNivelHabilidade());
    }

    @Test
    public void incluirJogadorComNivelDeHabilidadeMaiorCem(){
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
        desafio.incluirJogador(1L,1L,"Taina",LocalDate.of(1998,06,18),150, BigDecimal.valueOf(10));
        assertEquals(100, desafio.jogadores.get(0).getNivelHabilidade());
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
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
        desafio.incluirJogador(1L,1L,"Taina",LocalDate.of(1998,06,18),10, BigDecimal.valueOf(10));
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
    public void buscarMelhorJogadorDoTimeComPrimeiroJogador(){
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
        desafio.incluirJogador(2L,1L,"Taina",LocalDate.of(1998,06,18),10, BigDecimal.valueOf(10));
        desafio.incluirJogador(3L,1L,"Hand",LocalDate.of(1998,06,18),8, BigDecimal.valueOf(30));
        desafio.incluirJogador(1L,1L,"Stella",LocalDate.of(1998,06,18),7, BigDecimal.valueOf(30));
        assertEquals(Long.valueOf(2L), desafio.buscarMelhorJogadorDoTime(1L));
    }

    @Test
    public void buscarMelhorJogadorDoTimeComSegundoJogador(){
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
        desafio.incluirJogador(2L,1L,"Taina",LocalDate.of(1998,06,18),8, BigDecimal.valueOf(10));
        desafio.incluirJogador(3L,1L,"Hand",LocalDate.of(1998,06,18),10, BigDecimal.valueOf(30));
        desafio.incluirJogador(1L,1L,"Stella",LocalDate.of(1998,06,18),7, BigDecimal.valueOf(30));
        assertEquals(Long.valueOf(3L), desafio.buscarMelhorJogadorDoTime(1L));
    }

    @Test
    public void buscarMelhorJogadorDoTimeComTerceiroJogador(){
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
        desafio.incluirJogador(2L,1L,"Taina",LocalDate.of(1998,06,18),7, BigDecimal.valueOf(10));
        desafio.incluirJogador(3L,1L,"Hand",LocalDate.of(1998,06,18),8, BigDecimal.valueOf(30));
        desafio.incluirJogador(1L,1L,"Stella",LocalDate.of(1998,06,18),10, BigDecimal.valueOf(30));
        assertEquals(Long.valueOf(1L), desafio.buscarMelhorJogadorDoTime(1L));
    }

    @Test
    public void buscarMelhorJogadorDoTimeComValoresIguais(){
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
        desafio.incluirJogador(2L,1L,"Taina",LocalDate.of(1998,06,18),8, BigDecimal.valueOf(10));
        desafio.incluirJogador(3L,1L,"Hand",LocalDate.of(1998,06,18),10, BigDecimal.valueOf(30));
        desafio.incluirJogador(1L,1L,"Stella",LocalDate.of(1998,06,18),10, BigDecimal.valueOf(30));
        assertEquals(Long.valueOf(1L), desafio.buscarMelhorJogadorDoTime(1L));
    }
    @Test
    public void buscarMelhorJogadorDoTimeComTodosIguaisMenorIdNoMeio(){
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
        desafio.incluirJogador(5L,1L,"Taina",LocalDate.of(1998,06,18),10, BigDecimal.valueOf(10));
        desafio.incluirJogador(1L,1L,"Hand",LocalDate.of(1998,06,18),10, BigDecimal.valueOf(30));
        desafio.incluirJogador(3L,1L,"Stella",LocalDate.of(1998,06,18),10, BigDecimal.valueOf(30));
        assertEquals(Long.valueOf(1L), desafio.buscarMelhorJogadorDoTime(1L));
    }

    @Test
    public void buscarMelhorJogadorDoTimeComTodosIguaisMenorIDPorUltimo(){
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
        desafio.incluirJogador(5L,1L,"Taina",LocalDate.of(1998,06,18),10, BigDecimal.valueOf(10));
        desafio.incluirJogador(7L,1L,"Hand",LocalDate.of(1998,06,18),10, BigDecimal.valueOf(30));
        desafio.incluirJogador(1L,1L,"Stella",LocalDate.of(1998,06,18),10, BigDecimal.valueOf(30));
        assertEquals(Long.valueOf(1L), desafio.buscarMelhorJogadorDoTime(1L));
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void buscarMelhorJogadorDoTimeQueNaoExisteTime(){
        desafio.buscarMelhorJogadorDoTime(7L);
    }

    @Test
    public void buscarJogadorMaisVelhoComSucessoPrimeiro(){
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
        desafio.incluirJogador(2L,1L,"Taina",LocalDate.of(1998,06,18),10, BigDecimal.valueOf(10));
        desafio.incluirJogador(3L,1L,"Hand",LocalDate.of(1999,04,18),9, BigDecimal.valueOf(30));
        desafio.incluirJogador(1L,1L,"Stella",LocalDate.of(2001,06,18),9, BigDecimal.valueOf(30));
        assertEquals(Long.valueOf(2L), desafio.buscarJogadorMaisVelho(1L));
    }

    @Test
    public void buscarJogadorMaisVelhoComSucessoSegundo(){
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
        desafio.incluirJogador(3L,1L,"Hand",LocalDate.of(1999,04,18),9, BigDecimal.valueOf(30));
        desafio.incluirJogador(2L,1L,"Taina",LocalDate.of(1998,06,18),10, BigDecimal.valueOf(10));
        desafio.incluirJogador(1L,1L,"Stella",LocalDate.of(2001,06,18),9, BigDecimal.valueOf(30));
        assertEquals(Long.valueOf(2L), desafio.buscarJogadorMaisVelho(1L));
    }

    @Test
    public void buscarJogadorMaisVelhoComSucessoTerceiro(){
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
        desafio.incluirJogador(3L,1L,"Hand",LocalDate.of(1999,04,18),9, BigDecimal.valueOf(30));
        desafio.incluirJogador(1L,1L,"Stella",LocalDate.of(2001,06,18),9, BigDecimal.valueOf(30));
        desafio.incluirJogador(2L,1L,"Taina",LocalDate.of(1998,06,18),10, BigDecimal.valueOf(10));
        assertEquals(Long.valueOf(2L), desafio.buscarJogadorMaisVelho(1L));
    }

    @Test
    public void buscarJogadorMaisVelhoComIdadeIgualMenorIdAddPorUltimo(){
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
        desafio.incluirJogador(2L,1L,"Taina",LocalDate.of(1998,06,18),10, BigDecimal.valueOf(10));
        desafio.incluirJogador(3L,1L,"Hand",LocalDate.of(1999,04,18),9, BigDecimal.valueOf(30));
        desafio.incluirJogador(1L,1L,"Stella",LocalDate.of(1998,06,18),9, BigDecimal.valueOf(30));
        assertEquals(Long.valueOf(1L), desafio.buscarJogadorMaisVelho(1L));
    }

    @Test
    public void buscarJogadorMaisVelhoComIdadeIgualMenorIdAddPrimeiro(){
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
        desafio.incluirJogador(1L,1L,"Taina",LocalDate.of(1998,06,18),10, BigDecimal.valueOf(10));
        desafio.incluirJogador(3L,1L,"Hand",LocalDate.of(1999,04,18),9, BigDecimal.valueOf(30));
        desafio.incluirJogador(2L,1L,"Stella",LocalDate.of(1998,06,18),9, BigDecimal.valueOf(30));
        assertEquals(Long.valueOf(1L), desafio.buscarJogadorMaisVelho(1L));
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void buscarJogadorMaisVelhoDeTimeQueNaoExiste(){
        desafio.buscarJogadorMaisVelho(9L);
    }

    @Test
    public void buscarTimesComSucesso(){
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
        desafio.incluirTime(2L,"flamengo", LocalDate.of(1988,06,18),"vermelho", "preto");
        desafio.incluirTime(4L,"botafogo", LocalDate.of(1988,06,18),"branco", "preto");
        desafio.incluirTime(3L,"fluminense", LocalDate.of(1988,06,18),"vermelho", "preto");
        List<Long> lista = desafio.buscarTimes();
        assertEquals(lista.get(0), Long.valueOf(1L));
        assertEquals(lista.get(1), Long.valueOf(2L));
        assertEquals(lista.get(2), Long.valueOf(3L));
        assertEquals(lista.get(3), Long.valueOf(4L));
    }

    @Test
    public void buscarTopJogadoresComSucesso(){
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
        desafio.incluirTime(2L,"flamengo", LocalDate.of(1988,06,18),"vermelho", "preto");
        desafio.incluirJogador(1L,1L,"Taina",LocalDate.of(1998,06,18),10, BigDecimal.valueOf(10));
        desafio.incluirJogador(3L,1L,"Hand",LocalDate.of(1999,04,18),9, BigDecimal.valueOf(30));
        desafio.incluirJogador(2L,1L,"Stella",LocalDate.of(1998,06,18),4, BigDecimal.valueOf(30));
        desafio.incluirJogador(5L,2L,"Taina",LocalDate.of(1998,06,18),2, BigDecimal.valueOf(10));
        desafio.incluirJogador(4L,2L,"Hand",LocalDate.of(1999,04,18),7, BigDecimal.valueOf(30));
        desafio.incluirJogador(6L,2L,"Stella",LocalDate.of(1998,06,18),3, BigDecimal.valueOf(30));
        List<Long> lista = desafio.buscarTopJogadores(3);
        assertEquals(lista.get(0), Long.valueOf(1L));
        assertEquals(lista.get(1), Long.valueOf(3L));
        assertEquals(lista.get(2), Long.valueOf(4L));
    }

    @Test
    public void buscarCorCamisaTimeDeForaComCoresPrimariasDiferentes(){
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
        desafio.incluirTime(2L,"flamengo", LocalDate.of(1988,06,18),"vermelho", "preto");
        assertEquals("vermelho", desafio.buscarCorCamisaTimeDeFora(1L,2L));
    }

    @Test
    public void buscarCorCamisaTimeDeForaComCoresPrimariasIguais(){
        desafio.incluirTime(1L,"vasco", LocalDate.of(1988,06,18),"branco", "preto");
        desafio.incluirTime(2L,"flamengo", LocalDate.of(1988,06,18),"branco", "preto");
        assertEquals("preto", desafio.buscarCorCamisaTimeDeFora(1L,2L));
    }

    @Test
    public void deveIncluirTime_Test() {
        this.desafio.incluirTime(1L, "TIME1",  LocalDate.of(2000, 01, 01), "azul", "vermelho");
        assertEquals("TIME1", this.desafio.buscarNomeTime(1L));
    }

    @Test(expected = IdentificadorUtilizadoException.class)
    public void deveLancarExcessaoSeIdJaUtilizadoAoIncluirTime_Test() {
            Long id = 1L;
            this.desafio.incluirTime(id, "TIME1",  LocalDate.of(2000, 1, 1), "azul", "vermelho");
            this.desafio.incluirTime(id, "TIME2",  LocalDate.of(2000, 1, 1), "verde", "rosa");
    }

    @Test
    public void deveIncluirJogador_Test() {
        this.desafio.incluirTime(1L, "TIME1",  LocalDate.of(2000, 1, 1), "azul", "vermelho");
        this.desafio.incluirJogador(1L, 1L, "JOGADOR1", LocalDate.of(2000,1,1), 50, new BigDecimal(10000.00));
        assertEquals("JOGADOR1", this.desafio.buscarNomeJogador(1L));
    }

    @Test(expected = IdentificadorUtilizadoException.class)
    public void deveLancarExcessaoAoIncluirJogadorMesmoId_Test() {
            this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");
            this.desafio.incluirJogador(1L, 1L, "JOGADOR1", LocalDate.of(2000, 1, 1), 50, new BigDecimal(10000.00));
            this.desafio.incluirJogador(1L, 1L, "JOGADOR2", LocalDate.of(2000, 1, 1), 40, new BigDecimal(1000.00));
    }

    @Test(expected = IdentificadorUtilizadoException.class)
    public void deveLancarExcessaoAoIncluirJogadorMesmoIdTimesDiferentes_Test() {
            this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");
            this.desafio.incluirTime(2L, "TIME2", LocalDate.of(2000, 1, 1), "azul", "vermelho");
            this.desafio.incluirJogador(1L, 1L, "JOGADOR1", LocalDate.of(2000, 1, 1), 50, new BigDecimal(10000.00));
            this.desafio.incluirJogador(1L, 2L, "JOGADOR2", LocalDate.of(2000, 1, 1), 40, new BigDecimal(1000.00));
    }


    @Test(expected = TimeNaoEncontradoException.class)
    public void deveLancarExcessaoAoIncluirJogadorETimeNaoEncontrado_Test() {
            this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");
            this.desafio.incluirJogador(1L, 2L, "JOGADOR1", LocalDate.of(2000, 1, 1), 50, new BigDecimal(10000.00));
    }

    @Test
    public void deveDefinirCapitao_Test() {
        this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");

        this.desafio.incluirJogador(1L, 1L, "JOGADOR1", LocalDate.of(2000, 1, 1), 50, new BigDecimal(10000.00));
        this.desafio.incluirJogador(2L, 1L, "JOGADOR2", LocalDate.of(2000, 1, 1), 40, new BigDecimal(1000.00));

        this.desafio.definirCapitao(1L);

        assertEquals(Long.valueOf(1L), this.desafio.buscarCapitaoDoTime(1L));

        this.desafio.definirCapitao(2L);

        assertEquals(Long.valueOf(2L), this.desafio.buscarCapitaoDoTime(1L));

        this.desafio.definirCapitao(1L);

        assertEquals(Long.valueOf(1L), this.desafio.buscarCapitaoDoTime(1L));

    }
    @Test(expected = TimeNaoEncontradoException.class)
    public void deveLancarExcessaoAoBuscarCapitaoDoTimeETimeNaoForEncontrado_Test() {
            this.desafio.buscarCapitaoDoTime(1L);
    }

    @Test(expected = CapitaoNaoInformadoException.class)
    public void deveLancarExcessaoAoBuscarCapitaoDoTimeECapitaoNaoDefinido_Test() {
            this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");
            this.desafio.incluirJogador(1L, 1L, "JOGADOR1", LocalDate.of(2000, 1, 1), 50, new BigDecimal(10000.00));

            this.desafio.buscarCapitaoDoTime(1L);
    }

    @Test
    public void buscarNomeJogador_Test() {
        this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");

        this.desafio.incluirJogador(1L, 1L, "JOGADOR1", LocalDate.of(2000, 1, 1), 50, new BigDecimal(10000.00));

        assertEquals("JOGADOR1", this.desafio.buscarNomeJogador(1L));
    }

    @Test(expected = JogadorNaoEncontradoException.class)
    public void deveLancarExessaoAobuscarNomeJogadorENaoForEncontrado_Test() {
            this.desafio.buscarNomeJogador(1L);
    }

    @Test
    public void buscarNomeTime_Test() {
        this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");
        assertEquals("TIME1", this.desafio.buscarNomeTime(1L));
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void deveLancarExessaoAobuscarNomeTimeENaoForEncontrado_Test() {
            this.desafio.buscarNomeTime(1L);
    }


    @Test
    public void deveBuscarJogadoresDoTime_Test() {
        this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");

        this.desafio.incluirJogador(1L, 1L, "JOGADOR1", LocalDate.of(2000, 1, 1), 50, new BigDecimal(10000.00));
        this.desafio.incluirJogador(2L, 1L, "JOGADOR2", LocalDate.of(2000, 1, 1), 40, new BigDecimal(1000.00));
        this.desafio.incluirJogador(5L, 1L, "JOGADOR3", LocalDate.of(2000, 1, 1), 50, new BigDecimal(10000.00));
        this.desafio.incluirJogador(4L, 1L, "JOGADOR4", LocalDate.of(2000, 1, 1), 40, new BigDecimal(1000.00));
        this.desafio.incluirJogador(6L, 1L, "JOGADOR5", LocalDate.of(2000, 1, 1), 50, new BigDecimal(10000.00));
        this.desafio.incluirJogador(3L, 1L, "JOGADOR6", LocalDate.of(2000, 1, 1), 40, new BigDecimal(1000.00));

        List<Long> listaEsperada = Arrays.asList(1L,2L,3L,4L,5L,6L);

        assertEquals(listaEsperada, this.desafio.buscarJogadoresDoTime(1L));
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void deveLancarExcessaoAoBuscarJogadoresDoTimeETimeNaoEncontrado_Test() {
            this.desafio.buscarJogadoresDoTime(1L);
    }

    @Test
    public void deveBuscarMelhorJogadorDoTime_Test() {

        this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");

        this.desafio.incluirJogador(1L, 1L, "JOGADOR1", LocalDate.of(2000, 1, 1), 10, new BigDecimal(10000.00));
        this.desafio.incluirJogador(2L, 1L, "JOGADOR2", LocalDate.of(2000, 1, 1), 50, new BigDecimal(1000.00));
        this.desafio.incluirJogador(5L, 1L, "JOGADOR3", LocalDate.of(2000, 1, 1), 30, new BigDecimal(10000.00));
        this.desafio.incluirJogador(4L, 1L, "JOGADOR4", LocalDate.of(2000, 1, 1), 20, new BigDecimal(1000.00));
        this.desafio.incluirJogador(7L, 1L, "JOGADOR5", LocalDate.of(2000, 1, 1), 60, new BigDecimal(10000.00));
        this.desafio.incluirJogador(8L, 1L, "JOGADOR6", LocalDate.of(2000, 1, 1), 0, new BigDecimal(1000.00));

        assertEquals(Long.valueOf(7L), this.desafio.buscarMelhorJogadorDoTime(1L));

        this.desafio.incluirJogador(9L, 1L, "JOGADOR6", LocalDate.of(2000, 1, 1), 60, new BigDecimal(1000.00));
        assertEquals(Long.valueOf(7L), this.desafio.buscarMelhorJogadorDoTime(1L));
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void deveLancarExcessaoAoBuscarMelhorJogadorDoTimeETimeNaoEncontrado_Test() {
            this.desafio.buscarMelhorJogadorDoTime(1L);
    }

    @Test
    public void deveBuscarJogadorMaisVelho_Test() {

        this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");

        this.desafio.incluirJogador(1L, 1L, "JOGADOR1", LocalDate.of(2001, 1, 1), 10, new BigDecimal(10000.00));
        this.desafio.incluirJogador(2L, 1L, "JOGADOR2", LocalDate.of(2002, 1, 1), 50, new BigDecimal(1000.00));
        this.desafio.incluirJogador(5L, 1L, "JOGADOR3", LocalDate.of(2003, 1, 1), 30, new BigDecimal(10000.00));
        this.desafio.incluirJogador(4L, 1L, "JOGADOR4", LocalDate.of(2004, 1, 1), 20, new BigDecimal(1000.00));
        this.desafio.incluirJogador(7L, 1L, "JOGADOR5", LocalDate.of(2000, 1, 1), 60, new BigDecimal(10000.00));
        this.desafio.incluirJogador(8L, 1L, "JOGADOR6", LocalDate.of(2010, 1, 1), 0, new BigDecimal(1000.00));

        assertEquals(Long.valueOf(7L), this.desafio.buscarJogadorMaisVelho(1L));

        this.desafio.incluirJogador(6L, 1L, "JOGADOR6", LocalDate.of(2000, 1, 1), 70, new BigDecimal(1000.00));
        assertEquals(Long.valueOf(6L), this.desafio.buscarJogadorMaisVelho(1L));
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void deveLancarExcessaoAoBuscaJogadorMaisVelhoDoTimeETimeNaoEncontrado_Test() {
            this.desafio.buscarJogadorMaisVelho(1L);
    }

    @Test
    public void deveBuscarTimes_Test() {

        List<Long> listaEsperada = Arrays.asList();

        assertEquals(listaEsperada, this.desafio.buscarTimes());

        this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");
        this.desafio.incluirTime(5L, "TIME2", LocalDate.of(2000, 1, 1), "azul", "vermelho");
        this.desafio.incluirTime(2L, "TIME3", LocalDate.of(2000, 1, 1), "azul", "vermelho");
        this.desafio.incluirTime(6L, "TIME4", LocalDate.of(2000, 1, 1), "azul", "vermelho");
        this.desafio.incluirTime(3L, "TIME5", LocalDate.of(2000, 1, 1), "azul", "vermelho");
        this.desafio.incluirTime(4L, "TIME6", LocalDate.of(2000, 1, 1), "azul", "vermelho");


        listaEsperada = Arrays.asList(1L,2L,3L,4L,5L,6L);

        assertEquals(listaEsperada, this.desafio.buscarTimes());
    }

    @Test
    public void deveBuscarJogadorMaiorSalario_Test() {

        this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");

        this.desafio.incluirJogador(1L, 1L, "JOGADOR1", LocalDate.of(2001, 1, 1), 10, new BigDecimal(10000.00));
        this.desafio.incluirJogador(2L, 1L, "JOGADOR2", LocalDate.of(2002, 1, 1), 50, new BigDecimal(1000.00));
        this.desafio.incluirJogador(5L, 1L, "JOGADOR3", LocalDate.of(2003, 1, 1), 30, new BigDecimal(10000.00));
        this.desafio.incluirJogador(4L, 1L, "JOGADOR4", LocalDate.of(2004, 1, 1), 20, new BigDecimal(1000.00));
        this.desafio.incluirJogador(7L, 1L, "JOGADOR5", LocalDate.of(2000, 1, 1), 60, new BigDecimal(10000.00));
        this.desafio.incluirJogador(8L, 1L, "JOGADOR6", LocalDate.of(2010, 1, 1), 0, new BigDecimal(1000.00));

        assertEquals(Long.valueOf(1L), this.desafio.buscarJogadorMaiorSalario(1L));

        this.desafio.incluirJogador(9L, 1L, "JOGADOR6", LocalDate.of(2010, 1, 1), 0, new BigDecimal(100000.00));
        assertEquals(Long.valueOf(9L), this.desafio.buscarJogadorMaiorSalario(1L));
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void deveLancarExcessaoAoBuscarJogadorMaiorSalarioSemTime_Test() {
            this.desafio.buscarJogadorMaiorSalario(1L);
    }

    @Test
    public void deveBuscarSalarioDoJogador_Test() {


        this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");
        this.desafio.incluirTime(2L, "TIME2", LocalDate.of(2000, 1, 1), "azul", "vermelho");

        this.desafio.incluirJogador(1L, 1L, "JOGADOR1", LocalDate.of(2001, 1, 1), 10, new BigDecimal(10000.00));
        this.desafio.incluirJogador(2L, 1L, "JOGADOR2", LocalDate.of(2002, 1, 1), 50, new BigDecimal(1000.00));
        this.desafio.incluirJogador(5L, 1L, "JOGADOR3", LocalDate.of(2003, 1, 1), 30, new BigDecimal(10000.00));
        this.desafio.incluirJogador(4L, 2L, "JOGADOR4", LocalDate.of(2004, 1, 1), 20, new BigDecimal(1000.00));
        this.desafio.incluirJogador(7L, 2L, "JOGADOR5", LocalDate.of(2000, 1, 1), 60, new BigDecimal(10000.00));
        this.desafio.incluirJogador(8L, 1L, "JOGADOR6", LocalDate.of(2010, 1, 1), 0, new BigDecimal(1000.00));

        assertEquals(new BigDecimal(10000.00), this.desafio.buscarSalarioDoJogador(1L));
        assertEquals(new BigDecimal(1000.00), this.desafio.buscarSalarioDoJogador(2L));
        assertEquals(new BigDecimal(10000.00), this.desafio.buscarSalarioDoJogador(5L));
        assertEquals(new BigDecimal(1000.00), this.desafio.buscarSalarioDoJogador(4L));
        assertEquals(new BigDecimal(10000.00), this.desafio.buscarSalarioDoJogador(7L));
        assertEquals(new BigDecimal(1000.00), this.desafio.buscarSalarioDoJogador(8L));

    }


    @Test(expected = JogadorNaoEncontradoException.class)
    public void deveLancarExcessaoAoBuscarSalarioDoJogadorEJogadorNaoExistir_Test() {
            this.desafio.buscarSalarioDoJogador(1L);
    }

    @Test
    public void deveBuscarTopJogadores_Test() {

        List<Long> listaEsperada;

        this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");
        this.desafio.incluirTime(2L, "TIME2", LocalDate.of(2000, 1, 1), "azul", "vermelho");

        this.desafio.incluirJogador(1L, 1L, "JOGADOR1", LocalDate.of(2001, 1, 1), 10, new BigDecimal(10000.00));
        this.desafio.incluirJogador(2L, 2L, "JOGADOR2", LocalDate.of(2002, 1, 1), 50, new BigDecimal(1000.00));
        this.desafio.incluirJogador(5L, 1L, "JOGADOR3", LocalDate.of(2003, 1, 1), 30, new BigDecimal(10000.00));
        this.desafio.incluirJogador(4L, 2L, "JOGADOR4", LocalDate.of(2004, 1, 1), 20, new BigDecimal(1000.00));
        this.desafio.incluirJogador(7L, 2L, "JOGADOR5", LocalDate.of(2000, 1, 1), 60, new BigDecimal(10000.00));
        this.desafio.incluirJogador(8L, 1L, "JOGADOR6", LocalDate.of(2010, 1, 1), 0, new BigDecimal(1000.00));

        listaEsperada = Arrays.asList(7L, 2L, 5L, 4L, 1L, 8L);
        assertEquals(listaEsperada, this.desafio.buscarTopJogadores(6));

        this.desafio.incluirJogador(9L, 1L, "JOGADOR7", LocalDate.of(2010, 1, 1), 60, new BigDecimal(1000.00));

        listaEsperada = Arrays.asList(7L, 9L, 2L, 5L, 4L);

        assertEquals(listaEsperada, this.desafio.buscarTopJogadores(5));
    }

    @Test
    public void deveBuscarTopJogadoresVazio_Test() {

        List<Long> listaEsperada = new ArrayList<>();
        assertEquals(listaEsperada, this.desafio.buscarTopJogadores(1));
    }


    @Test
    public void deveBuscarCorCamisaTimeDeForaUniformesPrincipaisIguais_Test() {

        this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");
        this.desafio.incluirTime(2L, "TIME2", LocalDate.of(2000, 1, 1), "azul", "amarelo");
        assertEquals("amarelo", this.desafio.buscarCorCamisaTimeDeFora(1L, 2L));

    }


    @Test
    public void deveBuscarCorCamisaTimeDeForaUniformesPrincipaisDiferentes_Test() {

        this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");
        this.desafio.incluirTime(2L, "TIME2", LocalDate.of(2000, 1, 1), "verde", "amarelo");
        assertEquals("verde", this.desafio.buscarCorCamisaTimeDeFora(1L, 2L));

    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void deveLancarExcessaoAoBuscarCorCamisetaTimeDeForaCasoPrimeiroTimeNaoExista_Test() {
            this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");
            this.desafio.buscarCorCamisaTimeDeFora(3L, 1L);
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void deveLancarExcessaoAoBuscarCorCamisetaTimeDeForaCasoSegundoTimeNaoExista_Test() {
            this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");
            this.desafio.buscarCorCamisaTimeDeFora(1L, 3L);
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void deveLancarExcessaoAoBuscarCorCamisetaTimeDeForaCasoAmbosTimesNaoExistam_Test() {
            this.desafio.buscarCorCamisaTimeDeFora(1L, 3L);
    }
}
