package br.com.dicasdeumdev.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);

//		User user = new User(1, "Andre", "email@email.com", "123"); // prova que as notações estão funcionando na classe User
	}

}
