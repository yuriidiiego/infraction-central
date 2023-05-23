package br.com.project.infractioncentral.domain.infraction.payload.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.project.infractioncentral.domain.infraction.VehicleType;

public class InfractionRecordResponse {

  private Long id;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createdAt;

  private int speed;

  private String licensePlate;

  private VehicleType vehicleType;

  public InfractionRecordResponse() {}

  public InfractionRecordResponse(int speed, String licensePlate, VehicleType car) {
    this.speed = speed;
    this.licensePlate = licensePlate;
    this.vehicleType = car;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
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
