package br.com.project.infractioncentral.config;

import java.util.Random;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  Random random() {
    return new Random();
  }
}
