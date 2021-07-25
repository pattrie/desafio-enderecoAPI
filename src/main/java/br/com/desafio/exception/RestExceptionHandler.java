package br.com.desafio.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(RestException.class)
  public ResponseEntity handleException(RestException e) {
    DefaultError error = new DefaultError(e.getHttpStatus().value(), e.getMessage());
    return new ResponseEntity(error, e.getHttpStatus());
  }
}
