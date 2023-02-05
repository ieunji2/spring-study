package com.hello.springstudy.util.exhandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BasicException extends RuntimeException {

  ErrorCode errorCode;

  public BasicException() {
    super();
  }

  public BasicException(String message) {
    super(message);
  }

  public BasicException(String message, Throwable cause) {
    super(message, cause);
  }

  public BasicException(Throwable cause) {
    super(cause);
  }

  public BasicException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
