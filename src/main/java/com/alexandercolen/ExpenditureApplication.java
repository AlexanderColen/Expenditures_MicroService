package com.alexandercolen;

/**
 *
 * @author Alex
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({ "com.alexandercolen.rest", "com.alexandercolen.service" })
@EnableJpaRepositories("com.alexandercolen.dao")
@EntityScan("com.alexandercolen.domain")
@SpringBootApplication
public class ExpenditureApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ExpenditureApplication.class, args);
    }
}