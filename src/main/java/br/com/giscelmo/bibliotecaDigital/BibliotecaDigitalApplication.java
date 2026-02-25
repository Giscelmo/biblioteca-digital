package br.com.giscelmo.bibliotecaDigital;

import br.com.giscelmo.bibliotecaDigital.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BibliotecaDigitalApplication implements CommandLineRunner {
    private final Principal principal;

    public BibliotecaDigitalApplication(Principal principal) {
        this.principal = principal;
    }

    public static void main(String[] args) {
		SpringApplication.run(BibliotecaDigitalApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        principal.executar();
    }
}
