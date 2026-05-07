package br.com.unipds.filmes.telas;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public abstract class AbstractTelaTest {

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

    protected String obterSaidaDoConsole() {
        return consoleOutput.toString();
    }
}
