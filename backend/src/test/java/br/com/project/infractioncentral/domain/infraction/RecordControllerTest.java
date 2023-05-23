package br.com.project.infractioncentral.domain.infraction;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.project.infractioncentral.domain.infraction.payload.request.InfractionRecordRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
class RecordControllerTest extends BaseIT {

  @Container
  private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(
    "postgres:latest"
  )
    .withDatabaseName("infraction-test")
    .withUsername("postgres")
    .withPassword("k29DlaweP65");

  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
    registry.add("spring.datasource.username", postgresContainer::getUsername);
    registry.add("spring.datasource.password", postgresContainer::getPassword);
  }

  @Autowired
  private InfractionRecordRepository recordRepository;

  @Autowired
  private ObjectMapper objectMapper;

  @AfterEach
  public void tearDown() {
    recordRepository.deleteAll();
  }

  @Test
  void createRecord_success() throws Exception {
    InfractionRecordRequest recordRequest = new InfractionRecordRequest();
    recordRequest.setLicensePlate("ABC1D23");
    recordRequest.setSpeed(90);
    recordRequest.setVehicleType(VehicleType.CAR);

    mockMvc
      .perform(
        post("/records")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(recordRequest))
      )
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.id").isNumber())
      .andExpect(jsonPath("$.createdAt").isString())
      .andExpect(jsonPath("$.speed").value(recordRequest.getSpeed()))
      .andExpect(
        jsonPath("$.licensePlate").value(recordRequest.getLicensePlate())
      )
      .andExpect(
        jsonPath("$.vehicleType")
          .value(recordRequest.getVehicleType().toString())
      );
  }

  @Test
  void getRecords_success() throws Exception {
    InfractionRecord record = new InfractionRecord();
    record.setLicensePlate("ABC1D23");
    record.setSpeed(90);
    record.setVehicleType(VehicleType.CAR);
    recordRepository.save(record);

    mockMvc
      .perform(get("/records").accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.length()").value(1))
      .andExpect(jsonPath("$[0].id").value(record.getId()))
      .andExpect(jsonPath("$[0].speed").value(record.getSpeed()))
      .andExpect(jsonPath("$[0].licensePlate").value(record.getLicensePlate()))
      .andExpect(
        jsonPath("$[0].vehicleType").value(record.getVehicleType().toString())
      );
  }
}
