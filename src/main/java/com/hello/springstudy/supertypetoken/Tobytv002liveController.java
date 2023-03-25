package com.hello.springstudy.supertypetoken;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class Tobytv002liveController {

  @RequestMapping("/")
  public List<User> users() {
    return Arrays.asList(new User("A"), new User("B"), new User("C")); //[{"name":"A"},{"name":"B"},{"name":"C"}]
  }
}
