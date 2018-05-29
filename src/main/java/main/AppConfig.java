package main;

import com.fontys.capitaselecta.service.ExpenditureService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Alexander
 */
@Configuration
public class AppConfig {
    @Bean
    public ExpenditureService expenditureService() {
        return new ExpenditureService();
    }
}