package br.com.project.infractioncentral.config.error;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.project.infractioncentral.domain.infraction.InfractionRecordNotFoundException;

@RestControllerAdvice
public class ErrorExceptionHandler extends ResponseEntityExceptionHandler {

  private final MessageSource messageSource;

  public ErrorExceptionHandler(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
    MethodArgumentNotValidException ex,
    HttpHeaders headers,
    HttpStatus status,
    WebRequest request
  ) {
    List<ErrorMessage> errors = new ArrayList<>();
    List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
    fieldErrors.forEach(e -> {
      String message = messageSource.getMessage(
        e,
        LocaleContextHolder.getLocale()
      );
      int statusCode = HttpStatus.BAD_REQUEST.value();
      ErrorMessage errorMessage = new ErrorMessage(statusCode, message);

      errors.add(errorMessage);
    });

    return ResponseEntity.status(status).body(errors);
  }

  @ExceptionHandler(InfractionRecordNotFoundException.class)
  public ResponseEntity<ErrorMessage> handleDataIntegrityViolationException(
    InfractionRecordNotFoundException ex
  ) {
    int statusCode = HttpStatus.NOT_FOUND.value();
    ErrorMessage errorMessage = new ErrorMessage(statusCode, ex.getMessage());
    return ResponseEntity.status(statusCode).body(errorMessage);
  }
}
