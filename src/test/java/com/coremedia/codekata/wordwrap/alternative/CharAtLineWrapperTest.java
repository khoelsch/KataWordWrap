package com.coremedia.codekata.wordwrap.alternative;

import com.coremedia.codekata.wordwrap.CharAtLineWrapper;
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
