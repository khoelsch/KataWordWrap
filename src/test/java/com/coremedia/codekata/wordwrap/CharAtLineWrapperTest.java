package com.coremedia.codekata.wordwrap;

import org.junit.Before;

/**
 * Tests {@link com.coremedia.codekata.wordwrap.SplitLineWrapper}.
 */
public class CharAtLineWrapperTest extends AbstractLineWrapperTest {

  @Before
  public void setUp() throws Exception {
    wrapper = new CharAtLineWrapper();
  }
}
