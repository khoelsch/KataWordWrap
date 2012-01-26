package com.coremedia.codekata.wordwrap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LastIndexOfWrapperTest {

  private LastIndexOfWrapper wrapper;

  @Before
  public void setUp() throws Exception {
    wrapper = new LastIndexOfWrapper();
  }

  @Test
  public void emptyString() {
    Assert.assertEquals("", wrapper.wrap("", 5));
  }

  @Test
  public void dontWrapSmallLine() {
    String expected = "ab cd";
    String actual = wrapper.wrap("ab cd", 100);
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void simpleWrapAtSpace() {
    String expected = "ab\ncd";
    String actual = wrapper.wrap("ab cd", 3);
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void simpleWrapAfterFirstWord() {
    String expected = "ab\ncd";
    String actual = wrapper.wrap("ab cd", 3);
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void simpleWrapTwoTimesAtSpace() {
    String line = "ab cd ef";
    String expected = "ab\ncd\nef";
    String actual = wrapper.wrap(line, 3);
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void simpleForceWrap() {
    String line = "abcdef";
    String expected = "abc\ndef";
    String actual = wrapper.wrap(line, 3);
    Assert.assertEquals(expected, actual);
  }
}
