package br.com.project.infractioncentral.domain.infraction;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.infractioncentral.domain.infraction.payload.request.InfractionRecordRequest;
import br.com.project.infractioncentral.domain.infraction.payload.request.InfractionRecordUpdateRequest;
import br.com.project.infractioncentral.domain.infraction.payload.response.InfractionRecordResponse;

@Tag(name = "Records", description = "Infractions records management")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/records")
@RestController
public class InfractionRecordController {

  private final InfractionRecordService recordService;

  public InfractionRecordController(InfractionRecordService recordService) {
    this.recordService = recordService;
  }

  @Operation(
    summary = "Create a new record",
    method = "POST",
    operationId = "createRecord",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
      description = "Details of the new record",
      required = true,
      content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = InfractionRecordRequest.class)
      )
    ),
    responses = {
      @ApiResponse(
        responseCode = "201",
        description = "Record created",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = InfractionRecordResponse.class)
        )
      ),
      @ApiResponse(
        responseCode = "400",
        description = "Bad request",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = ErrorMessage.class)
        )
      ),
    }
  )
  @PostMapping
  public ResponseEntity<InfractionRecordResponse> createRecord(
    @RequestBody @Valid InfractionRecordRequest recordRequest
  ) {
    InfractionRecordResponse recordResponse = recordService.createRecord(recordRequest);
    return ResponseEntity
      .created(URI.create("/records/" + recordResponse.getId()))
      .body(recordResponse);
  }

  @Operation(
    summary = "Get all records",
    method = "GET",
    operationId = "getAllRecords",
    responses = {
      @ApiResponse(
        responseCode = "200",
        description = "Records found",
        content = @Content(
          mediaType = "application/json",
          array = @ArraySchema(
            schema = @Schema(implementation = InfractionRecordResponse.class)
          )
        )
      ),
    }
  )
  @GetMapping
  public ResponseEntity<List<InfractionRecordResponse>> getAllRecords() {
    return ResponseEntity.ok(recordService.getRecords());
  }

  @Operation(
    summary = "Get a record by id",
    method = "GET",
    operationId = "getRecord",
    responses = {
      @ApiResponse(
        responseCode = "200",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = InfractionRecordResponse.class)
        )
      ),
      @ApiResponse(
        responseCode = "404",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = ErrorMessage.class)
        )
      ),
    }
  )
  @GetMapping("/{id}")
  public ResponseEntity<InfractionRecordResponse> getRecordById(@PathVariable Long id) {
    return ResponseEntity.ok(recordService.getRecordById(id));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<InfractionRecordResponse> updateRecord(
    @PathVariable Long id,
    @RequestBody @Valid InfractionRecordUpdateRequest recordUpdateRequest
  ) {
    return ResponseEntity.ok(
      recordService.updateRecord(id, recordUpdateRequest)
    );
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
    recordService.deleteRecord(id);
    return ResponseEntity.noContent().build();
  }
}
