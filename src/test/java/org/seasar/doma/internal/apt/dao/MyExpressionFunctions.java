package org.seasar.doma.internal.apt.dao;

import org.seasar.doma.jdbc.dialect.H2Dialect.H2ExpressionFunctions;

/** @author taedium */
public class MyExpressionFunctions extends H2ExpressionFunctions {

  public String hello(String name) {
    return name;
  }
}
