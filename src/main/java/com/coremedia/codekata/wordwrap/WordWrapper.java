package com.coremedia.codekata.wordwrap;

public interface WordWrapper {
  /**
   * Wraps a string after the specified number of characters.
   *
   * @param lineToWrap the string representing the line to wrap
   * @param maxCharsPerLine the number of characters (columns) after a line break should be inserted
   * @return the wrapped line
   */
  String wrap(String lineToWrap, int maxCharsPerLine);
}
