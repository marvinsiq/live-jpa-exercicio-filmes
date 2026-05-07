package br.com.unipds.filmes.telas;

import br.com.unipds.filmes.model.Ator;
import br.com.unipds.filmes.repository.AtorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TelaCadastrarAtorTest extends AbstractTelaTest {

    @Test
    void deveCadastrarAtorComSucesso() {

        AtorRepository repoMock = Mockito.mock(AtorRepository.class);

        TelaCadastrarAtor telaCadastrarAtor = new TelaCadastrarAtor(repoMock);

        String nome = "Jack";
        String sobrenome = "Black";
        char sexo = 'M';

        String input = String.format("%s\n%s\n%s\n",
                nome, sobrenome, sexo);

        var entradaFake = new Scanner(input);

        telaCadastrarAtor.executar(entradaFake);

        ArgumentCaptor<Ator> atorCaptor = ArgumentCaptor.forClass(Ator.class);
        Mockito.verify(repoMock).save(atorCaptor.capture());

        Ator atorSalvo = atorCaptor.getValue();
        assertEquals(nome, atorSalvo.getNome());
        assertEquals(sobrenome, atorSalvo.getSobrenome());
        assertEquals(sexo, atorSalvo.getSexo());

        String saida = obterSaidaDoConsole();
        assertThat(saida).contains("Ator cadastrado com sucesso.");
    }

}