package org.seasar.doma.internal.apt.dao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/** @author taedium */
@Target(ElementType.CONSTRUCTOR)
public @interface ConstructorAnnotation2 {

  int aaa();

  boolean bbb();
}
