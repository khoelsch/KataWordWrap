package com.coremedia.codekata.wordwrap.alternative;

import com.coremedia.codekata.wordwrap.LineWrapper;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the {@link LineWrapper} interface.
 * To test your implementation of LineWrapper initialize 'wrapper' with an instance of your implementation.
 */
public abstract class AbstractLineWrapperTest {

  // initialize 'wrapper' with an instance of your LineWrapper implementation
  protected LineWrapper wrapper = null;

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

  @Test
  public void testUnwrappable() throws Exception {
    String line = "abcdefghijklmnopqrstuvwxy z";
    String expected = "abcdefghijklmnopqrstuvwxy\nz";
    Assert.assertEquals(expected, wrapper.wrap(line, 5));

    line = "abc defghijklmnopqrstuvwxyz";
    expected = "abc\ndefghijklmnopqrstuvwxyz";
    Assert.assertEquals(expected, wrapper.wrap(line, 5));
  }

  @Test
  public void testBoundaries1() throws Exception {
    String line = "abcd efghijk lmno pqrstuv wxy z";
    Assert.assertEquals(line, wrapper.wrap(line, -1));
    Assert.assertEquals(line, wrapper.wrap(line, 0));

    line = "a b cde f g";
    String expected = "a\nb\ncde\nf\ng";
    Assert.assertEquals(expected, wrapper.wrap(line, 1));

    line = "ab cdefg";
    expected = "ab\ncdefg";
    Assert.assertEquals(expected, wrapper.wrap(line, 7));

    expected = "ab cdefg";
    Assert.assertEquals(expected, wrapper.wrap(line, 8));

    expected = "ab cdefg";
    Assert.assertEquals(expected, wrapper.wrap(line, 9));

  }
}
