package br.com.unipds.filmes.telas;

import br.com.unipds.filmes.model.Ator;
import br.com.unipds.filmes.repository.AtorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class TelaBuscarAtorTest extends AbstractTelaTest {

    @Test
    void deveExibirMensagemQuandoOAtorNaoExiste() {
        AtorRepository repoMock = mock(AtorRepository.class);

        var tela = new TelaBuscarAtor(repoMock);
        var entradaFake = new Scanner("Jack Black\n");

        tela.executar(entradaFake);

        String saida = obterSaidaDoConsole();
        assertThat(saida).contains("Não foram encontrados atores com o parâmetro informado.");
    }

    @Test
    void deveMostrarOsDadosQuandoOAtorExiste() {
        AtorRepository repoMock = mock(AtorRepository.class);

        Ator ator = new Ator();
        ator.setNome("Jack");
        ator.setSobrenome("Black");
        ator.setSexo('M');

        Mockito.when(repoMock.findByNomeContainsIgnoreCaseOrderByNomeAsc("Jack Black"))
                .thenReturn(List.of(ator));

        var tela = new TelaBuscarAtor(repoMock);
        var entradaFake = new Scanner("Jack Black\n");

        tela.executar(entradaFake);

        String saida = obterSaidaDoConsole();
        assertThat(saida).contains("Resultado da busca");
        assertThat(saida).contains("Jack Black");
        assertThat(saida).contains("1 Ator(es) encontrado(s).");

        Mockito.verify(repoMock).findByNomeContainsIgnoreCaseOrderByNomeAsc("Jack Black");
        Mockito.verifyNoMoreInteractions(repoMock);
    }

}