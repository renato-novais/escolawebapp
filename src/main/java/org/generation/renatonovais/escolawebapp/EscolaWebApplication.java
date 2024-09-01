package org.generation.renatonovais.escolawebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/")
public class EscolaWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(EscolaWebApplication.class, args);
	}

}
