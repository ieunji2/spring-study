package com.hello.springstudy.supertypetoken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

public class SuperTypeToken2 {

  static class TypesafeMap {

    Map<TypeReference<?>, Object> map = new HashMap();

    <T> void put(TypeReference<T> tr, T value) {
      map.put(tr, value);
    }

    <T> T get(TypeReference<T> tr) {
      if (tr.type instanceof Class<?>)
        return ((Class<T>) tr.type).cast(map.get(tr)); //TypeReference<String>
      else
        return ((Class<T>) ((ParameterizedType) tr.type).getRawType()).cast(map.get(tr)); //TypeReference<List<String>>
    }
  }

  static class TypeReference<T> {
    Type type;

    public TypeReference() {
      Type stype = getClass().getGenericSuperclass();
      if (stype instanceof ParameterizedType) {
        this.type = ((ParameterizedType) stype).getActualTypeArguments()[0];
      } else {
        throw new RuntimeException();
      }
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass().getSuperclass() != o.getClass().getSuperclass()) return false;
      TypeReference<?> that = (TypeReference<?>) o;
      return type.equals(that.type);
    }

    @Override
    public int hashCode() {
      return Objects.hash(type);
    }
  }

  public static void main(String[] args) throws Exception {
    //TypeReference t = new TypeReference<String>() {};
    //System.out.println("t.type = " + t.type); //t.type = class java.lang.String

    TypesafeMap m = new TypesafeMap();
    m.put(new TypeReference<Integer>() {}, 1); //익명클래스 사용
    m.put(new TypeReference<String>() {}, "String");
    m.put(new TypeReference<List<Integer>>() {}, Arrays.asList(1, 2, 3)); //List<Integer>
    m.put(new TypeReference<List<String>>() {}, Arrays.asList("A", "B", "C")); //List<String>

    System.out.println(m.get(new TypeReference<Integer>() {})); //새로운 인스턴스 생성(==비교) equals, hashcode 재정의
    System.out.println(m.get(new TypeReference<String>() {}));
    System.out.println(m.get(new TypeReference<List<Integer>>() {})); //[1, 2, 3]
    System.out.println(m.get(new TypeReference<List<String>>() {})); //[A, B, C]
  }
}
