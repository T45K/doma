package org.seasar.doma.internal.apt.dao;

import java.util.List;
import org.seasar.doma.BatchUpdate;
import org.seasar.doma.Dao;

/** @author taedium */
@Dao(config = MyConfig.class)
public interface SqlFileBatchUpdateDao {

  @BatchUpdate(sqlFile = true, batchSize = 10)
  int[] update(List<String> names);
}
