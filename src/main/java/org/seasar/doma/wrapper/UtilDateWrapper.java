package org.seasar.doma.wrapper;

import java.util.Date;
import org.seasar.doma.DomaNullPointerException;

/**
 * {@link Date} のラッパーです。
 *
 * @author taedium
 * @since 1.9.0
 */
public class UtilDateWrapper extends AbstractWrapper<Date> {

  /** インスタンスを構築します。 */
  public UtilDateWrapper() {
    super(Date.class);
  }

  /**
   * 値を指定してインスタンスを構築します。
   *
   * @param value 値
   */
  public UtilDateWrapper(Date value) {
    super(Date.class, value);
  }

  @Override
  protected Date doGetCopy() {
    Date original = get();
    if (original == null) {
      return null;
    }
    return new Date(original.getTime());
  }

  @Override
  public <R, P, Q, TH extends Throwable> R accept(WrapperVisitor<R, P, Q, TH> visitor, P p, Q q)
      throws TH {
    if (visitor == null) {
      throw new DomaNullPointerException("visitor");
    }
    return visitor.visitUtilDateWrapper(this, p, q);
  }
}
