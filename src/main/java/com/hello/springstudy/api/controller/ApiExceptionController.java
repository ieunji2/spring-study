package com.hello.springstudy.api.controller;

import com.hello.springstudy.api.dto.HelloResponseDto;
import com.hello.springstudy.util.exhandler.BasicException;
import com.hello.springstudy.util.exhandler.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiExceptionController {

  @GetMapping("/api/members/{id}")
  public ResponseEntity<HelloResponseDto> getMember(@PathVariable("id") String id) {
    if (id.equals("bad")) {
      throw new IllegalArgumentException("잘못된 입력 값");
    }
    if (id.equals("basic-ex")) {
      throw new BasicException(ErrorCode.BAD);
    }
    HelloResponseDto helloResponseDto = HelloResponseDto.builder()
            .id(1L)
            .name("eunjiA")
            .build();
    return new ResponseEntity<>(helloResponseDto, HttpStatus.OK);
  }
}
