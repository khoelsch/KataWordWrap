package com.coremedia.codekata.wordwrap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LastIndexWrapperTest {

  private LastIndexWrapper wrapper;

  @Before
  public void setUp() throws Exception {
    wrapper = new LastIndexWrapper();
  }

  @Test
  public void emptyString() {
    Assert.assertEquals("", wrapper.wrap("", 5));
  }

  @Test
  public void doesNotWrap() {
    final String expected = "ab cd";
    final String actual = wrapper.wrap("ab cd", 10);
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void simpleWrap() {
    final String expected = "ab\ncd";
    final String actual = wrapper.wrap("ab cd", 3);
    Assert.assertEquals(expected, actual);
  }
}
