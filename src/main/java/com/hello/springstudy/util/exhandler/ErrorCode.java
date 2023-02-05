package com.hello.springstudy.util.exhandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
  BAD(HttpStatus.BAD_REQUEST, "잘못된 요청입니다.");

  private final HttpStatus httpStatus;
  private final String message;
}
