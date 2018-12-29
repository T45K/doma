package org.seasar.doma.internal.apt.dao;

import java.sql.Clob;
import org.seasar.doma.ClobFactory;
import org.seasar.doma.Dao;

/** @author taedium */
@Dao(config = MyConfig.class)
public interface ClobFactoryDao {

  @ClobFactory
  Clob create();
}
