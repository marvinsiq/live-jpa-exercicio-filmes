package br.com.unipds.filmes.telas;

import br.com.unipds.filmes.model.Filme;
import br.com.unipds.filmes.repository.FilmeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class TelaListarFilmesTest extends AbstractTelaTest {

    @Test
    void deveListarOsFilmesCadastrados() {

        FilmeRepository repoMock = Mockito.mock(FilmeRepository.class);

        String titulo = "Super Mario Galaxy: O Filme";
        String idioma = "Português";
        String pais = "BR";
        String dataStr = "10/05/2026";
        Integer ano = 2026;
        Integer duracao = 120;

        Filme filme = new Filme();
        filme.setTitulo(titulo);
        filme.setIdioma(idioma);
        filme.setPais(pais);
        filme.setDataLancamento(LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        filme.setAno(ano);
        filme.setDuracao(duracao);

        when(repoMock.obterFilmes()).thenReturn(List.of(filme));

        TelaListarFilmes telaListarFilmes = new TelaListarFilmes(repoMock);
        telaListarFilmes.executar(null);

        String saida = obterSaidaDoConsole();
        assertThat(saida).contains(titulo);
    }

    @Test
    void deveExibirMensagemQuandoNaoHouverFilmesCadastrados() {
        FilmeRepository repoMock = Mockito.mock(FilmeRepository.class);
        when(repoMock.obterFilmes()).thenReturn(List.of());

        TelaListarFilmes telaListarFilmes = new TelaListarFilmes(repoMock);
        telaListarFilmes.executar(null);

        String saida = obterSaidaDoConsole();
        assertThat(saida).contains("Não há filmes cadastrados.");
    }
}