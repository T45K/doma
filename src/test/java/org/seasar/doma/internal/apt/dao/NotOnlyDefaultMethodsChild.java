package org.seasar.doma.internal.apt.dao;

/** @author nakamura */
public interface NotOnlyDefaultMethodsChild<T> extends NotOnlyDefaultMethodsParent {

  default void bbb() {}

  default T ccc(T value) {
    return value;
  }
}
