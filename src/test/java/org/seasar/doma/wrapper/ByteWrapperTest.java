package org.seasar.doma.wrapper;

import junit.framework.TestCase;

/** @author taedium */
public class ByteWrapperTest extends TestCase {

  /** */
  public void testIncrement() {
    ByteWrapper wrapper = new ByteWrapper((byte) 10);
    wrapper.increment();
    assertEquals(new Byte((byte) 11), wrapper.get());
  }

  /** */
  public void testDecrement() {
    ByteWrapper wrapper = new ByteWrapper((byte) 10);
    wrapper.decrement();
    assertEquals(new Byte((byte) 9), wrapper.get());
  }
}
