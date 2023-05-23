package br.com.project.infractioncentral.domain.infraction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.com.project.infractioncentral.domain.infraction.payload.request.InfractionRecordRequest;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(properties = "api.url=http://localhost:8080/records")
class InfractionSimulatorServiceTest {

  @Mock
  private RestTemplate restTemplate;

  @InjectMocks
  private InfractionSimulatorService infractionSimulatorService;

  @Value("${api.url}")
  private static String URL_API;

  private Random random = new Random();

  @BeforeEach
  public void setUp() {
    infractionSimulatorService =
      new InfractionSimulatorService(restTemplate, random, URL_API);
  }

  @DisplayName("Deve retornar velocidade entre 80 e 100")
  @Test
  void shouldReturnSpeedBetween80And100() {
    int speed = infractionSimulatorService.generateRandomSpeedBetween80And100();
    assertTrue(speed >= 80 && speed <= 100);
  }

  @DisplayName("Deve gerar placa válida")
  @ParameterizedTest
  @ValueSource(strings = { "ABC1D23", "XYZ9K87", "MNO2R56" })
  void shouldGenerateValidLicensePlate(String licensePlate) {
    assertTrue(infractionSimulatorService.isValidLicensePlate(licensePlate));
    assertEquals(7, licensePlate.length());
    assertNotNull(licensePlate);
  }

  @DisplayName("Deve validar placa")
  @ParameterizedTest
  @ValueSource(strings = { "ABC1D23", "XYZ9K87", "MNO2R56" })
  void shouldValidateLicensePlate(String licensePlate) {
    assertTrue(infractionSimulatorService.isValidLicensePlate(licensePlate));
  }

  @DisplayName("Deve gerar placa com formato correto")
  @Test
  void shouldGenerateLicensePlateWithCorrectFormat() {
    String licensePlate = infractionSimulatorService.generateLicensePlate();
    assertTrue(licensePlate.matches("^[A-Z]{3}\\d[A-Z0-9]\\d{2}$"));
  }

  @DisplayName("Deve gerar tipo de veículo a partir do enum")
  @Test
  void shouldGenerateVehicleTypeFromEnum() {
    VehicleType vehicleType = infractionSimulatorService.generateVehicleType();
    assertTrue(
      vehicleType == VehicleType.CAR ||
      vehicleType == VehicleType.TRUCK ||
      vehicleType == VehicleType.MOTORCYCLE
    );
  }

  @DisplayName("Deve gerar RecordRequest com propriedades corretas")
  @Test
  void shouldGenerateRecordRequestWithCorrectProperties() {
    InfractionRecordRequest recordRequest = infractionSimulatorService.generateRecordRequest();
    assertNotNull(recordRequest);
    assertNotEquals(0, recordRequest.getSpeed());
    assertNotNull(recordRequest.getLicensePlate());
    assertNotNull(recordRequest.getVehicleType());
  }
}
