package br.com.grupodagostini.infractioncentral.domain.infraction.payload.request;

import br.com.grupodagostini.infractioncentral.config.validation.LicensePlate;
import br.com.grupodagostini.infractioncentral.domain.infraction.VehicleType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RecordRequest {

  @Min(value = 80, message = "Speed must be greater than or equal to 80 km/h")
  @Max(value = 100, message = "Speed must be less than or equal to 100  km/h")
  private int speed;

  @LicensePlate
  @NotBlank(message = "License Plate is required")
  private String licensePlate;

  @NotNull(message = "Vehicle Type is required")
  private VehicleType vehicleType;

  public RecordRequest() {}

  public RecordRequest(
    int speed,
    String licensePlate,
    VehicleType vehicleType
  ) {
    this.speed = speed;
    this.licensePlate = licensePlate;
    this.vehicleType = vehicleType;
  }

  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  public String getLicensePlate() {
    return licensePlate;
  }

  public void setLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
  }

  public VehicleType getVehicleType() {
    return vehicleType;
  }

  public void setVehicleType(VehicleType vehicleType) {
    this.vehicleType = vehicleType;
  }
}
