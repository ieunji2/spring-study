package com.hello.springstudy.supertypetoken;

public class User {

  String name;

  public User(String name) {
    this.name = name;
  }

  public User() {
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "User{" +
            "name='" + name + '\'' +
            '}';
  }
}
