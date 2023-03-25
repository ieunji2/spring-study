package com.hello.springstudy.supertypetoken;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeToken {

  static class TypesafeMap {

    Map<Class<?>, Object> map = new HashMap();

    <T> void put(Class<T> clazz, T value) {
      map.put(clazz, value);
    }

    <T> T get(Class<T> clazz) {
      return clazz.cast(map.get(clazz));
    }
  }

  public static void main(String[] args) throws Exception {

    TypesafeMap m = new TypesafeMap();
    m.put(Integer.class, 1);
    m.put(String.class, "String");
    m.put(List.class, Arrays.asList(1, 2, 3)); //List<Integer>
    m.put(List.class, Arrays.asList("A", "B", "C")); //List<String>

    System.out.println(m.get(Integer.class));
    System.out.println(m.get(String.class));
    System.out.println(m.get(List.class)); //[A, B, C]
  }
}
