package br.com.project.infractioncentral.domain.infraction;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "records")
public class InfractionRecord {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "speed")
  private int speed;

  @Column(name = "license_plate")
  private String licensePlate;

  @Enumerated(EnumType.STRING)
  @Column(name = "vehicle_type")
  private VehicleType vehicleType;

  public InfractionRecord() {}

  public InfractionRecord(int speed, String licensePlate, VehicleType vehicleType) {
    this.speed = speed;
    this.licensePlate = licensePlate;
    this.vehicleType = vehicleType;
    this.createdAt = LocalDateTime.now();
  }

  public InfractionRecord(
    int speed,
    String licensePlate,
    VehicleType vehicleType,
    LocalDateTime now
  ) {
    this.speed = speed;
    this.licensePlate = licensePlate;
    this.vehicleType = vehicleType;
    this.createdAt = now;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
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
