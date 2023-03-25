package com.hello.springstudy.supertypetoken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class SuperTypeToken {

  static class Sup<T> {
    T value;
  }

  static class Sub extends Sup<String> {
  }

  public static void main(String[] args) throws NoSuchFieldException {
    //인스턴스를 생성하면서 타입을 부여
    Sup<String> s = new Sup<>();
    //리플렉션을 이용한 타입 알아보기
    System.out.println(s.getClass().getDeclaredField("value").getType()); //class java.lang.Object

    //제네릭 타입을 상속 받아서 인스턴스를 생성
    Sub b = new Sub();
    //리플렉션을 이용한 타입 알아보기
    Type t = b.getClass().getGenericSuperclass();
    ParameterizedType ptype = (ParameterizedType) t;
    System.out.println(ptype.getActualTypeArguments()[0]); //class java.lang.String

    // 위의 코드를 익명클래스로 작성해보면
    //Sup c = new Sup<List<String>>() {};
    Type tt = (new Sup<List<String>>() {}).getClass().getGenericSuperclass();
    ParameterizedType ptypee = (ParameterizedType) tt;
    System.out.println(ptypee.getActualTypeArguments()[0]); //java.util.List<java.lang.String>
  }
}
