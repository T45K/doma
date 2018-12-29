package org.seasar.doma.jdbc.entity;

import java.util.function.Supplier;
import org.seasar.doma.jdbc.domain.DomainType;
import org.seasar.doma.wrapper.Wrapper;

/**
 * テナントIDのプロパティ型です。
 *
 * @author nakamura-to
 * @param <PARENT> 親エンティティの型
 * @param <ENTITY> エンティティの型
 * @param <BASIC> プロパティの基本型
 * @param <DOMAIN> プロパティのドメイン型
 */
public class TenantIdPropertyType<PARENT, ENTITY extends PARENT, BASIC, DOMAIN>
    extends DefaultPropertyType<PARENT, ENTITY, BASIC, DOMAIN> {

  /**
   * インスタンスを構築します。
   *
   * @param entityClass エンティティのクラス
   * @param entityPropertyClass プロパティのクラス
   * @param basicClass 基本型のクラス
   * @param wrapperSupplier ラッパーのサプライヤ
   * @param parentEntityPropertyType 親のエンティティのプロパティ型、親のエンティティを持たない場合 {@code null}
   * @param domainType ドメインのメタタイプ、ドメインでない場合 {@code null}
   * @param name プロパティの名前
   * @param columnName カラム名
   * @param namingType ネーミング規約
   * @param quoteRequired カラム名に引用符が必要とされるかどうか
   */
  public TenantIdPropertyType(
      Class<ENTITY> entityClass,
      Class<?> entityPropertyClass,
      Class<BASIC> basicClass,
      Supplier<Wrapper<BASIC>> wrapperSupplier,
      EntityPropertyType<PARENT, BASIC> parentEntityPropertyType,
      DomainType<BASIC, DOMAIN> domainType,
      String name,
      String columnName,
      NamingType namingType,
      boolean quoteRequired) {
    super(
        entityClass,
        entityPropertyClass,
        basicClass,
        wrapperSupplier,
        parentEntityPropertyType,
        domainType,
        name,
        columnName,
        namingType,
        true,
        true,
        quoteRequired);
  }

  @Override
  public boolean isTenantId() {
    return true;
  }
}
