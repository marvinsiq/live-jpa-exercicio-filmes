package br.com.unipds.filmes.telas;

import br.com.unipds.filmes.model.Filme;
import br.com.unipds.filmes.repository.FilmeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TelaCadastrarFilmeTest {

    private final ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
    private final PrintStream outOriginal = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(consoleOutput));
    }

    @AfterEach
    void tearDown() {
        System.setOut(outOriginal);
    }

    @Test
    void deveCadastrarFilmeComSucesso() {

        FilmeRepository repoMock = Mockito.mock(FilmeRepository.class);

        TelaCadastrarFilme telaCadastrarFilme = new TelaCadastrarFilme(repoMock);

        // Dados do filme para simular a entrada
        String titulo = "Super Mario Galaxy: O Filme";
        String idioma = "Português";
        String pais = "BR";
        String dataStr = "10/05/2026";
        Integer ano = 2026;
        Integer duracao = 120;

        String input = String.format("%s\n%s\n%s\n%s\n%d\n%d\n", 
                        titulo, idioma, pais, dataStr, ano, duracao);

        var entradaFake = new Scanner(input);

        telaCadastrarFilme.executar(entradaFake);

        ArgumentCaptor<Filme> filmeCaptor = ArgumentCaptor.forClass(Filme.class);
        Mockito.verify(repoMock).save(filmeCaptor.capture());

        Filme filmeSalvo = filmeCaptor.getValue();
        assertEquals(titulo, filmeSalvo.getTitulo());
        assertEquals(idioma, filmeSalvo.getIdioma());
        assertEquals(pais, filmeSalvo.getPais());
        assertEquals(LocalDate.of(2026, 5, 10), filmeSalvo.getDataLancamento());
        assertEquals(ano, filmeSalvo.getAno());
        assertEquals(duracao, filmeSalvo.getDuracao());

        String saida = consoleOutput.toString();
        assertThat(saida).contains("Filme cadastrado com sucesso.");
    }

}