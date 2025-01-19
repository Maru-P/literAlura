package maru.challenge3.liter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import maru.challenge3.liter.principal.Principal;
import maru.challenge3.liter.repository.LibroRepository;	

@SpringBootApplication
public class LiterApplication implements CommandLineRunner{

	@Autowired
	private LibroRepository lRepository;
	public static void main(String[] args) {
		SpringApplication.run(LiterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(lRepository);
		principal.muestraElMenu();
	}

}
