package org.seasar.doma;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ストアドファンクションやストアドプロシージャーへのINパラメータを示します。
 *
 * <p>このアノテーションが注釈されるパラメータは、 {@link Function} もしくは {@link Procedure} が注釈されたメソッドのパラメータでなければいけません。
 *
 * <h3>例:</h3>
 *
 * <pre>
 * &#064;Dao(config = AppConfig.class)
 * public interface EmployeeDao {
 *
 *     &#064;Procedure
 *     void updateSalary(@In Integer id, @In BigDecimal salary);
 * }
 * </pre>
 *
 * @author taedium
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface In {}
