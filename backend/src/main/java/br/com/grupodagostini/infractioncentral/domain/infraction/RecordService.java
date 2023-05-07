package br.com.grupodagostini.infractioncentral.domain.infraction;

import br.com.grupodagostini.infractioncentral.domain.infraction.payload.request.RecordRequest;
import br.com.grupodagostini.infractioncentral.domain.infraction.payload.response.RecordResponse;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RecordService {

  private final RecordRepository recordRepository;
  private final RecordMapper recordMapper;

  public RecordService(
    RecordRepository recordRepository,
    RecordMapper recordMapper
  ) {
    this.recordRepository = recordRepository;
    this.recordMapper = recordMapper;
  }

  public RecordResponse createRecord(RecordRequest recordRequest) {
    Record infractionRecord = recordMapper.toEntity(recordRequest);
    recordRepository.save(infractionRecord);
    return recordMapper.toResponse(infractionRecord);
  }

  public List<RecordResponse> getRecords() {
    return recordMapper.toResponseList(recordRepository.findAll());
  }
}
