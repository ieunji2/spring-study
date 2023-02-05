package com.hello.springstudy.util.exhandler;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BasicResult {
  private int status;
  private String code;
  private String message;

  public static BasicResult result(ErrorCode e) {
    return BasicResult.builder()
            .status(e.getHttpStatus().value())
            .code(e.name())
            .message(e.getMessage())
            .build();
  }
}
