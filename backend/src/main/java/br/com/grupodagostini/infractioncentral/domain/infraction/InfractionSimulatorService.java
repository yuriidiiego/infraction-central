package br.com.grupodagostini.infractioncentral.domain.infraction;

import br.com.grupodagostini.infractioncentral.domain.infraction.payload.request.RecordRequest;
import br.com.grupodagostini.infractioncentral.domain.infraction.payload.response.RecordResponse;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class InfractionSimulatorService {

  private final Logger log = LoggerFactory.getLogger(
    InfractionSimulatorService.class
  );

  private static final String CHARACTERS =
    "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  private static final int PLATE_LENGTH = 7;
  private static final String PLATE_PATTERN = "^[A-Z]{3}\\d[A-Z0-9]\\d{2}$";

  private final RestTemplate restTemplate;
  private final Random random;
  private final String urlApi;

  public InfractionSimulatorService(
    RestTemplate restTemplate,
    Random random,
    @Value("${api.url}") String urlApi
  ) {
    this.restTemplate = restTemplate;
    this.random = random;
    this.urlApi = urlApi;
  }

  @Scheduled(fixedRate = 3000)
  public void sendInfractionToAPI() {
    RecordRequest recordRequest = createRecordRequest();

    try {
      restTemplate.postForObject(urlApi, recordRequest, RecordResponse.class);
    } catch (RestClientException e) {
      log.error("Error sending infraction to API", e);
    }
  }

  private RecordRequest createRecordRequest() {
    int speed = generateSpeed();
    String licensePlate = generateLicensePlate();
    VehicleType vehicleType = generateVehicleType();

    return new RecordRequest(speed, licensePlate, vehicleType);
  }

  private int generateSpeed() {
    return random.nextInt(21) + 80;
  }

  private String generateLicensePlate() {
    String plate = generateRandomString(PLATE_LENGTH, CHARACTERS);

    if (!plate.matches(PLATE_PATTERN)) {
      return generateLicensePlate();
    }

    return plate;
  }

  private VehicleType generateVehicleType() {
    return VehicleType.values()[random.nextInt(VehicleType.values().length)];
  }

  private String generateRandomString(int length, String characters) {
    StringBuilder builder = new StringBuilder();

    for (int i = 0; i < length; i++) {
      builder.append(getRandomChar(characters));
    }

    return builder.toString();
  }

  private char getRandomChar(String characters) {
    int index = random.nextInt(characters.length());
    return characters.charAt(index);
  }
}
