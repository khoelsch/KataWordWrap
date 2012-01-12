package com.coremedia.codekata.wordwrap.alternative;

import com.coremedia.codekata.wordwrap.SplitLineWrapper;
import org.junit.Before;

/**
 * Tests {@link com.coremedia.codekata.wordwrap.SplitLineWrapper}.
 */
public class SplitLineWrapperTest extends AbstractLineWrapperTest {

  @Before
  public void setUp() throws Exception {
    wrapper = new SplitLineWrapper();
  }
}
