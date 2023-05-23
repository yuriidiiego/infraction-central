package br.com.project.infractioncentral.domain.infraction;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;

import br.com.project.infractioncentral.domain.infraction.payload.request.InfractionRecordRequest;
import br.com.project.infractioncentral.domain.infraction.payload.request.InfractionRecordUpdateRequest;
import br.com.project.infractioncentral.domain.infraction.payload.response.InfractionRecordResponse;

@Service
public class InfractionRecordService {

  private final InfractionRecordRepository recordRepository;
  private final InfractionRecordMapper recordMapper;

  public InfractionRecordService(
    InfractionRecordRepository recordRepository,
    InfractionRecordMapper recordMapper
  ) {
    this.recordRepository = recordRepository;
    this.recordMapper = recordMapper;
  }

  public InfractionRecordResponse createRecord(InfractionRecordRequest recordRequest) {
    InfractionRecord infractionRecord = recordMapper.toEntity(recordRequest);
    infractionRecord.setCreatedAt(LocalDateTime.now());
    recordRepository.save(infractionRecord);
    return recordMapper.toResponse(infractionRecord);
  }

  public List<InfractionRecordResponse> getRecords() {
    return recordMapper.toResponseList(recordRepository.findAll());
  }

  public InfractionRecordResponse getRecordById(Long id) {
    return recordMapper.toResponse(
      recordRepository
        .findById(id)
        .orElseThrow(() -> new InfractionRecordNotFoundException(id))
    );
  }

  public void deleteRecord(Long id) {
    getRecordById(id);
    recordRepository.deleteById(id);
  }

  @PatchMapping
  public InfractionRecordResponse updateRecord(
    Long id,
    InfractionRecordUpdateRequest recordUpdateRequest
  ) {
    InfractionRecord infractionRecord = getRecordByIdOrElseThrow(id);
    recordMapper.updateRecord(recordUpdateRequest, infractionRecord);
    return recordMapper.toResponse(recordRepository.save(infractionRecord));
  }

  public void deleteAllRecords() {
    recordRepository.deleteAll();
  }

  private InfractionRecord getRecordByIdOrElseThrow(Long id) {
    return recordRepository
      .findById(id)
      .orElseThrow(() -> new InfractionRecordNotFoundException(id));
  }
}
