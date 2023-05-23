package br.com.project.infractioncentral;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class InfractionCentralApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(InfractionCentralApiApplication.class, args);
  }
}
