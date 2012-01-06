package com.coremedia.codekata.wordwrap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SimpleWordWrapperTest {
  private SimpleWordWrapper wrapper;

  @Before
  public void setUp() throws Exception {
    wrapper = new SimpleWordWrapper();
  }

  @Test
  public void dontBreakShortLine() {
    String line = "Please don't wrap me!";

    Assert.assertEquals(line, wrapper.wrap(line, 100));
  }

  @Test
  public void wrapOneTime() {
    String line = "Please wrap me!";
    String expected = "Please\nwrap me!";

    String actual = wrapper.wrap(line, 10);

    Assert.assertEquals(expected, actual);
  }
}
