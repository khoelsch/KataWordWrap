package com.coremedia.codekata.wordwrap;

import org.junit.Assert;
import org.junit.Test;

public class CharArrayLineWrapperTest {
  private LineWrapper wrapper = new CharArrayLineWrapper();

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
