package com.generation.lojagames;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class LojagamesApplication {

	public static void main(String[] args) {

		try {
			// Tentar carregar .env apenas em ambiente de desenvolvimento
			Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

			dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
		} catch (Exception e) {
			// Em produção, as variáveis virão das configurações do Render
			System.out.println("Running without .env file - using environment variables");
		}

		SpringApplication.run(LojagamesApplication.class, args);
	}

}
