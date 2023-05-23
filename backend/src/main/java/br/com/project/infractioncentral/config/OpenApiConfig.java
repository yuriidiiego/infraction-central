package br.com.project.infractioncentral.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@Configuration
@OpenAPIDefinition(
  info = @Info(
    title = "Infraction Central API",
    version = "1.0",
    description = "A RESTful API for managing traffic offense records",
    contact = @Contact(
      name = "Yuri Nascimento",
      email = "yuriidiiego@gmail.com",
      url = "http://github.com/yuriidiiego"
    ),
    license = @License(
      name = "Apache 2.0",
      url = "http://www.apache.org/licenses/LICENSE-2.0.html"
    )
  ),
  servers = @Server(url = "http://localhost:8080", description = "Local server")
)
public class OpenApiConfig {}
