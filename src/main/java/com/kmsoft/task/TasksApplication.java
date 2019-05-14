package com.kmsoft.task;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.kmsoft.task.domain.Task;
import com.kmsoft.task.service.TaskService;

@SpringBootApplication
public class TasksApplication {

	public static void main(String[] args) {
		SpringApplication.run(TasksApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(TaskService taskService) {

		return args -> {
			taskService.save(new Task(1L, "Definir les fonctionalités attendues", LocalDate.now(), false));
			taskService.save(new Task(2L, "Faire la conception technique", LocalDate.now(), false));
			taskService.save(new Task(3L, "Preparer l'environnement de travail", LocalDate.now(), true));
			taskService.save(
					new Task(4L, "Créer l'architecture du projet et des bases de données ", LocalDate.now(), false));
			taskService.save(new Task(5L, "Faire le codages des fonctionalités", LocalDate.now(), true));
			taskService.save(new Task(6L, "Créer les apis nécessaires", LocalDate.now(), false));
			taskService.save(new Task(7L, "Mettre en place les Test Unitaire pour chaque fonctionalité",
					LocalDate.now(), false));
			taskService.save(new Task(8L, "Lancé les test unitaires", LocalDate.now(), false));
			taskService.save(new Task(9L, "Tester les Apis", LocalDate.now(), false));
			taskService.save(
					new Task(10L, "Ecrire les scripts de deploiment en environnement de Dev", LocalDate.now(), false));
		};
	}

}
