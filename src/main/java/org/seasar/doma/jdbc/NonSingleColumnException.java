package org.seasar.doma.jdbc;

import org.seasar.doma.message.Message;

/**
 * 1列であることを期待する検索系SQLの結果が1列でない場合にスローされる例外です。
 *
 * @author taedium
 * @since 1.5.0
 */
public class NonSingleColumnException extends JdbcException {

  private static final long serialVersionUID = 1L;

  /** SQLの種別 */
  protected final SqlKind kind;

  /** 未加工SQL */
  protected final String rawSql;

  /** フォーマット済みSQL */
  protected final String formattedSql;

  /** SQLファイルのパス */
  protected final String sqlFilePath;

  /**
   * 1列でない結果を返したSQLを指定してインスタンスを構築します。
   *
   * @param logType ログタイプ
   * @param sql SQL
   */
  public NonSingleColumnException(SqlLogType logType, Sql<?> sql) {
    this(logType, sql.getKind(), sql.getRawSql(), sql.getFormattedSql(), sql.getSqlFilePath());
  }

  /**
   * 1列でない結果を返した未加工SQLとフォーマット済みSQLを指定してインスタンスを構築します。
   *
   * @param logType ログタイプ
   * @param kind SQLの種別
   * @param rawSql 未加工SQL
   * @param formattedSql フォーマット済みSQL
   * @param sqlFilePath SQLファイルのパス
   */
  public NonSingleColumnException(
      SqlLogType logType, SqlKind kind, String rawSql, String formattedSql, String sqlFilePath) {
    super(Message.DOMA2006, sqlFilePath, choiceSql(logType, rawSql, formattedSql));
    this.kind = kind;
    this.rawSql = rawSql;
    this.formattedSql = formattedSql;
    this.sqlFilePath = sqlFilePath;
  }

  /**
   * SQLの種別を返します。
   *
   * @return SQLの種別
   * @since 1.5.0
   */
  public SqlKind getKind() {
    return kind;
  }

  /**
   * 未加工SQLを返します。
   *
   * @return 未加工SQL
   */
  public String getRawSql() {
    return rawSql;
  }

  /**
   * フォーマット済みSQLを返します。
   *
   * @return フォーマット済みSQL
   */
  public String getFormattedSql() {
    return formattedSql;
  }

  /**
   * SQLファイルのパスを返します。
   *
   * @return SQLファイルのパス、SQLが自動生成された場合 {@code null}
   */
  public String getSqlFilePath() {
    return sqlFilePath;
  }
}
