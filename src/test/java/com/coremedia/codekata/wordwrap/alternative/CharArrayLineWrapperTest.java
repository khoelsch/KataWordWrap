package com.coremedia.codekata.wordwrap.alternative;

import com.coremedia.codekata.wordwrap.CharArrayLineWrapper;
import org.junit.Before;

/**
 * Tests {@link com.coremedia.codekata.wordwrap.SplitLineWrapper}.
 */
public class CharArrayLineWrapperTest extends AbstractLineWrapperTest {

  @Before
  public void setUp() throws Exception {
    wrapper = new CharArrayLineWrapper();
  }
}
