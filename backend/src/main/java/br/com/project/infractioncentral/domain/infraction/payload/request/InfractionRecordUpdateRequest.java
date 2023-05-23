package br.com.project.infractioncentral.domain.infraction.payload.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import br.com.project.infractioncentral.config.validation.LicensePlate;
import br.com.project.infractioncentral.domain.infraction.VehicleType;

public class InfractionRecordUpdateRequest {

  @Min(value = 80, message = "Speed must be greater than or equal to 80 km/h")
  @Max(value = 100, message = "Speed must be less than or equal to 100  km/h")
  private int speed;

  @LicensePlate
  private String licensePlate;

  private VehicleType vehicleType;

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
