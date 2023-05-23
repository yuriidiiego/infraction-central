package br.com.project.infractioncentral.domain.infraction;

import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import br.com.project.infractioncentral.domain.infraction.payload.request.InfractionRecordRequest;
import br.com.project.infractioncentral.domain.infraction.payload.request.InfractionRecordUpdateRequest;
import br.com.project.infractioncentral.domain.infraction.payload.response.InfractionRecordResponse;

@Mapper(componentModel = "spring")
public interface InfractionRecordMapper {
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  InfractionRecord toEntity(InfractionRecordUpdateRequest recordUpdateRequest);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  InfractionRecord toEntity(InfractionRecordRequest recordRequest);

  InfractionRecordResponse toResponse(InfractionRecord infractionRecord);

  List<InfractionRecordResponse> toResponseList(List<InfractionRecord> infractionRecords);

  @BeanMapping(
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
  )
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  InfractionRecord updateRecord(
    InfractionRecordUpdateRequest recordUpdateRequest,
    @MappingTarget InfractionRecord infractionRecord
  );
}
