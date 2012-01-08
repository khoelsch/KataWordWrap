package com.coremedia.codekata.wordwrap;

import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * This class provides data-driven testing of LineWrappers. To add a new LineWrapper implementation to all tests
 * just add a constructor call to your new implementation into the {@link #data()} method.
 */
public class DataDrivenLineWrapperTest {
  protected LineWrapper wrapper;

  public DataDrivenLineWrapperTest(final LineWrapper wrapper) {
    this.wrapper = wrapper;
  }

  @Parameterized.Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(
            new Object[]{new SplitLineWrapper()},
            new Object[]{new RreLineWrapper()});
  }
}
