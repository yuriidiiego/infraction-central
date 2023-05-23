package br.com.project.infractioncentral.domain.infraction;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.project.infractioncentral.domain.infraction.payload.request.InfractionRecordRequest;
import br.com.project.infractioncentral.domain.infraction.payload.response.InfractionRecordResponse;

@Service
public class InfractionSimulatorService {

  private static final String CHARACTERS =
    "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  private static final int PLATE_LENGTH = 7;
  private static final String PLATE_PATTERN = "^[A-Z]{3}\\d[A-Z0-9]\\d{2}$";

  private final RestTemplate restTemplate;
  private final Random random;
  private final String urlApi;
  private final Logger log = LoggerFactory.getLogger(
    InfractionSimulatorService.class
  );

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
    InfractionRecordRequest recordRequest = generateRecordRequest();

    try {
      restTemplate.postForObject(
        urlApi,
        recordRequest,
        InfractionRecordResponse.class
      );
      log.info("Infraction created: {}", recordRequest);
    } catch (RestClientException e) {
      log.error("Error sending infraction to API", e);
    }
  }

  InfractionRecordRequest generateRecordRequest() {
    int speed = generateRandomSpeedBetween80And100();
    String licensePlate = generateLicensePlate();
    VehicleType vehicleType = generateVehicleType();
    return new InfractionRecordRequest(speed, licensePlate, vehicleType);
  }

  int generateRandomSpeedBetween80And100() {
    return random.nextInt(21) + 80;
  }

  String generateLicensePlate() {
    String plate = generateRandomString(PLATE_LENGTH, CHARACTERS);
    return isValidLicensePlate(plate) ? plate : generateLicensePlate();
  }

  boolean isValidLicensePlate(String licensePlate) {
    return licensePlate.matches(PLATE_PATTERN);
  }

  VehicleType generateVehicleType() {
    VehicleType[] vehicleTypes = VehicleType.values();
    int randomIndex = random.nextInt(vehicleTypes.length);
    return vehicleTypes[randomIndex];
  }

  private String generateRandomString(int length, String characters) {
    StringBuilder builder = new StringBuilder();

    for (int i = 0; i < length; i++) {
      char randomChar = getRandomChar(characters);
      builder.append(randomChar);
    }

    return builder.toString();
  }

  private char getRandomChar(String characters) {
    int randomIndex = random.nextInt(characters.length());
    return characters.charAt(randomIndex);
  }
}
