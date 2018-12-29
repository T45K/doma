package org.seasar.doma;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.PreparedStatement;
import java.sql.Statement;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.JdbcException;
import org.seasar.doma.jdbc.OptimisticLockException;
import org.seasar.doma.jdbc.SqlFileNotFoundException;
import org.seasar.doma.jdbc.SqlLogType;
import org.seasar.doma.jdbc.UniqueConstraintException;

/**
 * バッチ更新処理を示します。
 *
 * <p>このアノテーションが注釈されるメソッドは、Daoインタフェースのメンバでなければいけません。
 *
 * <h3>例:</h3>
 *
 * <pre>
 * &#064;Entity
 * public class Employee {
 *     ...
 * }
 *
 * &#064;Dao(config = AppConfig.class)
 * public interface EmployeeDao {
 *
 *     &#064;BatchUpdate
 *     int[] update(List&lt;Employee&gt; employee);
 * }
 * </pre>
 *
 * 注釈されるメソッドは、次の例外をスローすることがあります。
 *
 * <ul>
 *   <li>{@link DomaNullPointerException} パラメータに {@code null} を渡した場合
 *   <li>{@link OptimisticLockException} 楽観的排他制御が有効で更新件数が0件の場合
 *   <li>{@link UniqueConstraintException} 一意制約違反が発生した場合
 *   <li>{@link SqlFileNotFoundException} {@code sqlFile} 要素の値が {@code true} で、SQLファイルが見つからなかった場合
 *   <li>{@link JdbcException} 上記以外でJDBCに関する例外が発生した場合
 * </ul>
 *
 * @author taedium
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@DaoMethod
public @interface BatchUpdate {

  /**
   * SQLファイルにマッピングするかどうかを返します。
   *
   * @return SQLファイルにマッピングするかどうか
   */
  boolean sqlFile() default false;

  /**
   * クエリタイムアウト（秒）を返します。
   *
   * <p>指定しない場合、{@link Config#getQueryTimeout()}が使用されます。
   *
   * @return クエリタイムアウト（秒）
   * @see Statement#setQueryTimeout(int)
   */
  int queryTimeout() default -1;

  /**
   * バッチサイズを返します。
   *
   * <p>指定しない場合、{@link Config#getBatchSize()}が使用されます。
   *
   * <p>{@link PreparedStatement#executeBatch()} を実行する際のバッチサイズです。 バッチ対象の数がバッチサイズを上回る場合、バッチサイズの数だけ
   * {@link PreparedStatement#addBatch()} を呼び出し、 {@link PreparedStatement#executeBatch()}
   * を実行するということを繰り返します。
   *
   * @return バッチサイズ
   * @see PreparedStatement#addBatch()
   * @since 1.21.0
   */
  int batchSize() default -1;

  /**
   * 楽観的排他制御用のバージョン番号を無視するかどうかを返します。
   *
   * <p>{@code true} の場合、更新条件にバージョン番号を含めません。
   *
   * @return 楽観的排他制御用のバージョン番号を無視するかどうか
   * @since 1.7.0
   */
  boolean ignoreVersion() default false;

  /**
   * UPDATE文のSET句に含めるプロパティ名の配列を返します。
   *
   * <p>ここに指定できるのは、カラム名ではなく更新対象エンティティクラスのプロパティ名です。
   *
   * <p>この要素に対する指定は、{@link #sqlFile()} が {@code false} の場合にのみ有効です。
   *
   * @return UPDATE文のSET句に含めるプロパティ名の配列
   */
  String[] include() default {};

  /**
   * UPDATE文のSET句から除外するプロパティ名の配列を返します。
   *
   * <p>ここに指定できるのは、カラム名ではなく更新対象エンティティクラスのプロパティ名です。
   *
   * <p>この要素に対する指定は、{@link #sqlFile()} が {@code false} の場合にのみ有効です。
   *
   * @return UPDATE文のSET句から除外するプロパティ名の配列
   */
  String[] exclude() default {};

  /**
   * 更新結果が1件でない場合にスローされる {@link OptimisticLockException} を抑制するかどうかを返します。
   *
   * <p>この要素に対する指定は、{@link #sqlFile()} が {@code false} の場合にのみ有効です。
   *
   * @return 更新結果が1件でない場合にスローされる {@link OptimisticLockException} を抑制するかどうか
   */
  boolean suppressOptimisticLockException() default false;

  /**
   * SQLのログの出力形式を返します。
   *
   * @return SQLログの出力形式
   * @since 2.0.0
   */
  SqlLogType sqlLog() default SqlLogType.FORMATTED;
}
