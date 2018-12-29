package org.seasar.doma.jdbc.entity;

import java.lang.reflect.Method;
import org.seasar.doma.Delete;
import org.seasar.doma.DomaNullPointerException;
import org.seasar.doma.jdbc.Config;

/**
 * 削除処理の後処理のコンテキストです。
 *
 * @param <E> エンティティの型
 * @author taedium
 * @since 1.11.0
 */
public interface PostDeleteContext<E> {

  /**
   * エンティティのメタタイプを返します。
   *
   * @return エンティティのメタタイプ
   */
  public EntityType<E> getEntityType();

  /**
   * {@link Delete} が注釈されたメソッドを返します。
   *
   * @return メソッド
   * @since 1.27.0
   */
  public Method getMethod();

  /**
   * JDBCに関する設定を返します。
   *
   * @return JDBCに関する設定
   * @since 1.27.0
   */
  public Config getConfig();

  /**
   * 新しいエンティティを返します。
   *
   * @return 新しいエンティティ
   * @since 1.35.0
   */
  public E getNewEntity();

  /**
   * 新しいエンティティを設定します。
   *
   * <p>このメソッドは、 {@link PostDeleteContext#getEntityType()} に対応するエンティティがイミュータブルである場合にのみ利用してください。
   *
   * @param newEntity エンティティ
   * @throws DomaNullPointerException 引数が {@code null} の場合
   * @since 1.34.0
   */
  public void setNewEntity(E newEntity);
}
