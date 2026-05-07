package br.com.unipds.filmes;

import br.com.unipds.filmes.telas.LoopPrincipal;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ProjetoFilmesApplication implements CommandLineRunner{
	@Autowired
	private LoopPrincipal principal;

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		dotenv.entries().forEach(entry -> {
			if (System.getProperty(entry.getKey()) == null && System.getenv(entry.getKey()) == null) {
				System.setProperty(entry.getKey(), entry.getValue());
			}
		});
		SpringApplication.run(ProjetoFilmesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		principal.loop();
		System.exit(0);
		
	}
}
