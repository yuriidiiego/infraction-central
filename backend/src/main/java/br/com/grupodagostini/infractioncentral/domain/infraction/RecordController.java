package br.com.grupodagostini.infractioncentral.domain.infraction;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.grupodagostini.infractioncentral.config.error.ErrorMessage;
import br.com.grupodagostini.infractioncentral.domain.infraction.payload.request.RecordRequest;
import br.com.grupodagostini.infractioncentral.domain.infraction.payload.response.RecordResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Records", description = "Infractions records management")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/records")
@RestController
public class RecordController {

  private final RecordService recordService;

  public RecordController(RecordService recordService) {
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
        schema = @Schema(implementation = RecordRequest.class)
      )
    ),
    responses = {
      @ApiResponse(
        responseCode = "201",
        description = "Record created",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = RecordResponse.class)
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
  public ResponseEntity<RecordResponse> createRecord(
    @RequestBody @Valid RecordRequest recordRequest
  ) {
    RecordResponse recordResponse = recordService.createRecord(recordRequest);
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
        content = @Content(
          mediaType = "application/json",
          array = @ArraySchema(
            schema = @Schema(implementation = RecordResponse.class)
          )
        )
      ),
    }
  )
  @GetMapping
  public ResponseEntity<List<RecordResponse>> getAllRecords() {
    return ResponseEntity.ok(recordService.getRecords());
  }
}
