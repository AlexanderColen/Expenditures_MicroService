package main;

/**
 *
 * @author Alex
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({ "com.fontys.capitaselecta.rest", "com.fontys.capitaselecta.service" })
@EnableJpaRepositories("com.fontys.capitaselecta.dao")
@EntityScan("com.fontys.capitaselecta.domain")
@SpringBootApplication
public class Application {
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}