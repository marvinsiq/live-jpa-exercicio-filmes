package br.com.unipds.filmes.telas;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class LoopPrincipalTest extends AbstractTelaTest {

    @Test
    void deveExibirMenuESair() {
        Map<String, Tela> telas = new HashMap<>();
        LoopPrincipal loopPrincipal = new LoopPrincipal(telas);

        // Simula a entrada do usuário: 0 (Sair)
        System.setIn(new java.io.ByteArrayInputStream("0\n".getBytes()));

        loopPrincipal.loop();

        String saida = obterSaidaDoConsole();
        assertThat(saida).contains("MENU PRINCIPAL");
        assertThat(saida).contains("1 - Buscar filme");
        assertThat(saida).contains("0 - Sair");
        assertThat(saida).contains("Fim do programa!");
    }

    @Test
    void deveChamarTelaBuscarFilme() {
        Tela telaBuscarFilmeMock = mock(Tela.class);
        Map<String, Tela> telas = new HashMap<>();
        telas.put("telaBuscarFilme", telaBuscarFilmeMock);

        LoopPrincipal loopPrincipal = new LoopPrincipal(telas);

        // Simula a entrada do usuário: 1 (Buscar filme), depois Enter (voltarMenu), depois 0 (Sair)
        System.setIn(new java.io.ByteArrayInputStream("1\n\n0\n".getBytes()));

        loopPrincipal.loop();

        verify(telaBuscarFilmeMock).executar(any());
        
        String saida = obterSaidaDoConsole();
        assertThat(saida).contains("MENU PRINCIPAL");
        assertThat(saida).contains("Fim do programa!");
    }

    @Test
    void deveExibirMensagemOpcaoInvalida() {
        Map<String, Tela> telas = new HashMap<>();
        LoopPrincipal loopPrincipal = new LoopPrincipal(telas);

        // Simula a entrada do usuário: 99 (Invalida), depois Enter (voltarMenu), depois 0 (Sair)
        System.setIn(new java.io.ByteArrayInputStream("99\n\n0\n".getBytes()));

        loopPrincipal.loop();

        String saida = obterSaidaDoConsole();
        assertThat(saida).contains("Opção inválida!");
        assertThat(saida).contains("Pressione ENTER para continuar...");
    }
    @Test
    void deveChamarTelaCadastrarAtor() {
        Tela telaCadastrarAtorMock = mock(Tela.class);
        Map<String, Tela> telas = new HashMap<>();
        telas.put("telaCadastrarAtor", telaCadastrarAtorMock);

        LoopPrincipal loopPrincipal = new LoopPrincipal(telas);

        // Simula a entrada do usuário: 5 (Incluir ator), depois Enter (voltarMenu), depois 0 (Sair)
        System.setIn(new java.io.ByteArrayInputStream("5\n\n0\n".getBytes()));

        loopPrincipal.loop();

        verify(telaCadastrarAtorMock).executar(any());
        
        String saida = obterSaidaDoConsole();
        assertThat(saida).contains("MENU PRINCIPAL");
        assertThat(saida).contains("Fim do programa!");
    }
}