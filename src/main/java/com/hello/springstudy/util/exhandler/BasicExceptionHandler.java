package com.hello.springstudy.util.exhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class BasicExceptionHandler {

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<?> illegalExHandle(IllegalArgumentException e) {
    log.error("[illegalExHandle]", e);
    ErrorResult errorResult = new ErrorResult("BAD", e.getMessage());
    return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(BasicException.class)
  public ResponseEntity<?> basicExHandler(BasicException e) {
    log.error("[basicExHandler]", e);
    return new ResponseEntity<>(BasicResult.result(e.getErrorCode()), e.getErrorCode().getHttpStatus());
  }
}
