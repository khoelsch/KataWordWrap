package com.coremedia.codekata.wordwrap;

import org.junit.Before;

/**
 * Tests {@link com.coremedia.codekata.wordwrap.RreLineWrapper}.
 */
public class RreLineWrapperTest extends AbstractLineWrapperTest {

  @Before
  public void setUp() throws Exception {
    wrapper = new RreLineWrapper();
  }
}
