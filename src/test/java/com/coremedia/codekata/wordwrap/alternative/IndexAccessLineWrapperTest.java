package com.coremedia.codekata.wordwrap.alternative;

import com.coremedia.codekata.wordwrap.IndexAccessLineWrapper;
import com.coremedia.codekata.wordwrap.RreLineWrapper2;
import org.junit.Before;

/**
 * Tests {@link com.coremedia.codekata.wordwrap.RreLineWrapper2}.
 */
public class IndexAccessLineWrapperTest extends AbstractLineWrapperTest {

  @Before
  public void setUp() throws Exception {
    wrapper = new IndexAccessLineWrapper();
  }
}
