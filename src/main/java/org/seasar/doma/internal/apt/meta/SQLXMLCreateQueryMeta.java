package org.seasar.doma.internal.apt.meta;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import org.seasar.doma.internal.apt.mirror.SQLXMLFactoryMirror;

/** @author nakamura-to */
public class SQLXMLCreateQueryMeta extends AbstractCreateQueryMeta {

  protected SQLXMLFactoryMirror sqlxmlFactoryMirror;

  public SQLXMLCreateQueryMeta(ExecutableElement method, TypeElement dao) {
    super(method, dao);
  }

  SQLXMLFactoryMirror getSqlxmlFactoryMirror() {
    return sqlxmlFactoryMirror;
  }

  void setSqlxmlFactoryMirror(SQLXMLFactoryMirror sqlxmlFactoryMirror) {
    this.sqlxmlFactoryMirror = sqlxmlFactoryMirror;
  }
}
