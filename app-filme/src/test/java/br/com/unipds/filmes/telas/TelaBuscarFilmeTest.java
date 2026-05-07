package br.com.unipds.filmes.telas;

import br.com.unipds.filmes.model.Filme;
import br.com.unipds.filmes.repository.FilmeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class TelaBuscarFilmeTest extends AbstractTelaTest {

    @Test
    void deveExibirMensagemQuandoOFilmeNaoExiste() {
        FilmeRepository repoMock = mock(FilmeRepository.class);

        var tela = new TelaBuscarFilme(repoMock);
        var entradaFake = new Scanner("Matrix\n");

        tela.executar(entradaFake);

        String saida = obterSaidaDoConsole();
        assertThat(saida).contains("Não foram encontrados filmes");
    }

    @Test
    void deveMostrarOsDadosQuandoOFilmeExiste() {
        FilmeRepository repoMock = mock(FilmeRepository.class);

        var filme = new Filme();
        filme.setTitulo("Top Gun: Maverick");

        Mockito.when(repoMock.findByTituloContainsIgnoreCaseOrderByTituloAsc("Top Gun: Maverick"))
                .thenReturn(List.of(filme));

        var tela = new TelaBuscarFilme(repoMock);
        var entradaFake = new Scanner("Top Gun: Maverick\n");

        tela.executar(entradaFake);

        String saida = obterSaidaDoConsole();
        assertThat(saida).contains("Resultado da busca");
        assertThat(saida).contains("Top Gun: Maverick");
        assertThat(saida).contains("1 filme(s) encontrado(s).");

        Mockito.verify(repoMock).findByTituloContainsIgnoreCaseOrderByTituloAsc("Top Gun: Maverick");
        Mockito.verifyNoMoreInteractions(repoMock);
    }

}