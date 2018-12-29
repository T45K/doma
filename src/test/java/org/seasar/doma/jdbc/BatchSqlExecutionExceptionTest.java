package org.seasar.doma.jdbc;

import junit.framework.TestCase;

/** @author taedium */
public class BatchSqlExecutionExceptionTest extends TestCase {

  public void test() throws Exception {
    BatchSqlExecutionException e =
        new BatchSqlExecutionException(
            SqlLogType.FORMATTED,
            SqlKind.UPDATE,
            "aaa",
            "bbb",
            new Exception(),
            new RuntimeException());
    assertSame(SqlKind.UPDATE, e.getKind());
    assertEquals("aaa", e.getRawSql());
    assertEquals("bbb", e.getSqlFilePath());
    assertNull(e.getFormattedSql());
    assertNotNull(e.getRootCause());
    System.out.println(e.getMessage());
  }
}
