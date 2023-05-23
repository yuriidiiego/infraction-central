package br.com.project.infractioncentral.domain.infraction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import br.com.project.infractioncentral.InfractionCentralApiApplication;

@SpringBootTest(
  classes = InfractionCentralApiApplication.class,
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
public abstract class BaseIT {

  @Autowired
  public MockMvc mockMvc;
}
