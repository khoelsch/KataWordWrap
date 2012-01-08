package com.coremedia.codekata.wordwrap;

public interface LineWrapper {

  /**
   * Wraps a string after the specified number of characters.
   * <p/>
   * Wrapping means simply adding "\n" at the appropriate places.
   *
   * @param lineToWrap      the string representing the line to wrap
   * @param maxCharsPerLine the number of characters (columns) after a line break should be inserted
   * @return the wrapped line
   */
  String wrap(String lineToWrap, int maxCharsPerLine);
}
