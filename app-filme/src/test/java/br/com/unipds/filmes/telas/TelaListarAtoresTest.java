package br.com.unipds.filmes.telas;

import br.com.unipds.filmes.model.Ator;
import br.com.unipds.filmes.model.Filme;
import br.com.unipds.filmes.repository.AtorRepository;
import br.com.unipds.filmes.repository.FilmeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TelaListarAtoresTest extends AbstractTelaTest {

    @Test
    void deveListarOsAtoresCadastrados() {

        AtorRepository repoMock = Mockito.mock(AtorRepository.class);

        String nome = "Jack";
        String sobrenome = "Black";
        char sexo = 'M';

        Ator ator = new Ator();
        ator.setNome(nome);
        ator.setSobrenome(sobrenome);
        ator.setSexo(sexo);

        when(repoMock.findAll()).thenReturn(List.of(ator));

        TelaListarAtores telaListarFilmes = new TelaListarAtores(repoMock);
        telaListarFilmes.executar(null);

        String saida = obterSaidaDoConsole();
        assertThat(saida).contains(nome + " " + sobrenome);
    }

    @Test
    void deveExibirMensagemQuandoNaoHouverAtoresCadastrados() {
        AtorRepository repoMock = Mockito.mock(AtorRepository.class);
        when(repoMock.findAll()).thenReturn(List.of());

        TelaListarAtores telaListarFilmes = new TelaListarAtores(repoMock);
        telaListarFilmes.executar(null);

        String saida = obterSaidaDoConsole();
        assertThat(saida).contains("Não há nenhum ator cadastrado.");
    }
}