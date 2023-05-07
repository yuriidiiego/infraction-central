package br.com.grupodagostini.infractioncentral.domain.infraction;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.grupodagostini.infractioncentral.domain.infraction.payload.request.RecordRequest;
import br.com.grupodagostini.infractioncentral.domain.infraction.payload.response.RecordResponse;

@Component
public class RecordMapper {

  public Record toEntity(RecordRequest recordRequest) {
    if (recordRequest == null) {
      return null;
    }

    Record infractionRecord = new Record();
    infractionRecord.setSpeed(recordRequest.getSpeed());
    infractionRecord.setLicensePlate(recordRequest.getLicensePlate());
    infractionRecord.setVehicleType(recordRequest.getVehicleType());
    infractionRecord.setCreatedAt(LocalDateTime.now());

    return infractionRecord;
  }

  public RecordResponse toResponse(Record infractionRecord) {
    if (infractionRecord == null) {
      return null;
    }

    RecordResponse recordResponse = new RecordResponse();
    recordResponse.setId(infractionRecord.getId());
    recordResponse.setSpeed(infractionRecord.getSpeed());
    recordResponse.setLicensePlate(infractionRecord.getLicensePlate());
    recordResponse.setVehicleType(infractionRecord.getVehicleType());
    recordResponse.setCreatedAt(infractionRecord.getCreatedAt());

    return recordResponse;
  }

  public List<RecordResponse> toResponseList(List<Record> infractionRecords) {
    if (infractionRecords == null) {
      return Collections.emptyList();
    }

    return infractionRecords.stream().map(this::toResponse).collect(Collectors.toList());
  }
}
