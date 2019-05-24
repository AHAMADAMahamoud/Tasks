package com.kmsoft.task;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.kmsoft.task.domain.Task;
import com.kmsoft.task.service.TaskService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.Formatter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

@SpringBootApplication
@EntityScan({ "com.kmsoft.task.domain" })
@EnableJpaRepositories({ "com.kmsoft.task.repository" })
public class Config {

    /**
     * main run by java -jar nom.jar
     *
     * @param args
     *            Not use here
     */
    public static void main(final String[] args) {
        SpringApplication.run(Config.class, args);
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
