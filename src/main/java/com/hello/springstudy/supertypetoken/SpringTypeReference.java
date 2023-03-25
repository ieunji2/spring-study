package com.hello.springstudy.supertypetoken;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class SpringTypeReference {
  public static void main(String[] args) {
    RestTemplate rt = new RestTemplate();
    //List<User> users = rt.getForObject("http://localhost:8080", List.class); //ClassCastException - class java.util.LinkedHashMap cannot be cast to class com.hello.springstudy.supertypetoken.User
    //System.out.println(users.get(0).getName());
    //List users = rt.getForObject("http://localhost:8080", List.class);
    //System.out.println("users = " + users); //[{name=A}, {name=B}, {name=C}]
    //List<Map> users = rt.getForObject("http://localhost:8080", List.class);
    //System.out.println(users.get(0).get("name")); //A
    List<User> users = rt.exchange("http://localhost:8080", HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
    }).getBody();
    users.forEach(System.out::println);
  }
}
