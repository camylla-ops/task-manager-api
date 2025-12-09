package br.com.oliveira.task_manager_api;

import br.com.oliveira.task_manager_api.service.ExternalApiService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TaskManagerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerApiApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(ExternalApiService externalApiService) {
		return args -> {
			externalApiService.seedDatabase();
		};
	}

}