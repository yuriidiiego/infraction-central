package br.com.project.infractioncentral.domain.infraction;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InfractionRecordNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public InfractionRecordNotFoundException(Long id) {
    super("Record not found with id " + id);
  }
}
