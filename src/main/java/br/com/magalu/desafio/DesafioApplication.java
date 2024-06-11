package br.com.magalu.desafio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class DesafioApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(DesafioApplication.class);

	public static void main(String[] args) {

		LOGGER.info("API Desafio iniciando. . . ");

		SpringApplication.run(DesafioApplication.class, args);

		LOGGER.info("Iniciada com sucesso! ツ ");
	}

}
