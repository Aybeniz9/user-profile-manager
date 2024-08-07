package az.edu.turing.userprofilemanager.feign;

import feign.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;


@Configuration


public class FeignConfig {
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL; // This will log all details of requests and responses
    }
}
