package org.seasar.doma.jdbc.id;

import java.sql.Statement;
import org.seasar.doma.GenerationType;
import org.seasar.doma.jdbc.JdbcException;

/**
 * 識別子のジェネレータです。
 *
 * <p>{@link #generatePreInsert(IdGenerationConfig)} と {@link
 * #generatePostInsert(IdGenerationConfig, Statement)} のどちらか片方が {@code null} ではない値を返さなければいけません。
 *
 * <p>このインタフェースの実装は、引数なしの {@code public} なコンストラクタを持たなければいけません。
 *
 * <p>このインタフェースの実装はスレッドセーフでなければいけません。
 *
 * @author taedium
 */
public interface IdGenerator {

  /**
   * バッチ処理をサポートしているかどうかを返します。
   *
   * @param config 識別子生成の設定
   * @return サポートしている場合 {@code true}
   */
  boolean supportsBatch(IdGenerationConfig config);

  /**
   * {@link Statement#getGeneratedKeys()} をサポートしているかどうかを返します。
   *
   * @param config 識別子生成の設定
   * @return サポートしている場合 {@code true}
   */
  boolean supportsAutoGeneratedKeys(IdGenerationConfig config);

  /**
   * INSERT文にIDENTITYカラムを含めるべきかどうかを返します。
   *
   * @param config 識別子生成の設定
   * @return サポートしている場合 {@code true}
   */
  boolean includesIdentityColumn(IdGenerationConfig config);

  /**
   * INSERTの実行前に識別子を生成します。
   *
   * @param config 識別子生成の設定
   * @return 生成された識別子、サポートしていない場合 {@code null}
   * @throws JdbcException 識別子の生成に失敗した場合
   */
  Long generatePreInsert(IdGenerationConfig config);

  /**
   * INSERTの実行後に識別子を生成します。
   *
   * @param config 識別子生成の設定
   * @param statement INSERT文を実行した文
   * @return 生成された識別子、サポートしていない場合 {@code null}
   * @throws JdbcException 識別子の生成に失敗した場合
   */
  Long generatePostInsert(IdGenerationConfig config, Statement statement);

  /**
   * 識別子を生成する方法を返します。
   *
   * @return 識別子を生成する方法
   */
  GenerationType getGenerationType();
}
