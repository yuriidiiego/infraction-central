package br.com.grupodagostini.infractioncentral.config.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class ErrorMessage {

  private int statusCode;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime timestamp;

  private String message;

  public ErrorMessage(int statusCode, String message) {
    this.statusCode = statusCode;
    this.timestamp = LocalDateTime.now();
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public int getStatusCode() {
    return statusCode;
  }
}
