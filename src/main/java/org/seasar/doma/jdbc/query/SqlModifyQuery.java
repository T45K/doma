package org.seasar.doma.jdbc.query;

import static org.seasar.doma.internal.util.AssertionUtil.assertNotNull;

import java.util.LinkedHashMap;
import java.util.Map;
import org.seasar.doma.internal.expr.ExpressionEvaluator;
import org.seasar.doma.internal.expr.Value;
import org.seasar.doma.internal.jdbc.sql.NodePreparedSqlBuilder;
import org.seasar.doma.jdbc.PreparedSql;
import org.seasar.doma.jdbc.SqlExecutionSkipCause;
import org.seasar.doma.jdbc.SqlKind;
import org.seasar.doma.jdbc.SqlLogType;
import org.seasar.doma.jdbc.SqlNode;

/** @author taedium */
public abstract class SqlModifyQuery extends AbstractQuery implements ModifyQuery {

  protected final SqlKind kind;

  protected SqlNode sqlNode;

  protected final Map<String, Value> parameters = new LinkedHashMap<String, Value>();

  protected PreparedSql sql;

  protected boolean optimisticLockCheckRequired;

  protected SqlLogType sqlLogType;

  protected SqlModifyQuery(SqlKind kind) {
    assertNotNull(kind);
    this.kind = kind;
  }

  @Override
  public void prepare() {
    super.prepare();
    assertNotNull(sqlNode);
    prepareOptions();
    prepareSql();
    assertNotNull(sql);
  }

  protected void prepareOptions() {
    if (queryTimeout <= 0) {
      queryTimeout = config.getQueryTimeout();
    }
  }

  protected void prepareSql() {
    ExpressionEvaluator evaluator =
        new ExpressionEvaluator(
            parameters, config.getDialect().getExpressionFunctions(), config.getClassHelper());
    NodePreparedSqlBuilder sqlBuilder =
        new NodePreparedSqlBuilder(config, kind, null, evaluator, sqlLogType);
    sql = sqlBuilder.build(sqlNode, this::comment);
  }

  @Override
  public void complete() {}

  public void setSqlNode(SqlNode sqlNode) {
    this.sqlNode = sqlNode;
  }

  public void addParameter(String name, Class<?> type, Object value) {
    assertNotNull(name, type);
    parameters.put(name, new Value(type, value));
  }

  public void clearParameters() {
    parameters.clear();
  }

  public void setSqlLogType(SqlLogType sqlLogType) {
    this.sqlLogType = sqlLogType;
  }

  @Override
  public PreparedSql getSql() {
    return sql;
  }

  @Override
  public boolean isOptimisticLockCheckRequired() {
    return optimisticLockCheckRequired;
  }

  @Override
  public boolean isExecutable() {
    return true;
  }

  @Override
  public SqlExecutionSkipCause getSqlExecutionSkipCause() {
    return null;
  }

  @Override
  public boolean isAutoGeneratedKeysSupported() {
    return false;
  }

  @Override
  public SqlLogType getSqlLogType() {
    return sqlLogType;
  }

  @Override
  public String toString() {
    return sql != null ? sql.toString() : null;
  }
}
