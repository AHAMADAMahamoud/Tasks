package com.kmsoft.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 
 * (Description)
 *
 * @since 18 juil. 2019
 * @author mohamed.hanafi
 */
@SpringBootApplication
@EntityScan({"com.kmsoft.task.domain"})
@EnableJpaRepositories({"com.kmsoft.task.repository"})
public class Config {

    public static void main(final String[] args) {
        SpringApplication.run(Config.class, args);
    }

}
