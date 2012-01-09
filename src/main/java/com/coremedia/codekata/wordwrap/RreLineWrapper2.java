package com.coremedia.codekata.wordwrap;

/**
 * LineWrapper implementation by rre
 */
public final class RreLineWrapper2 implements LineWrapper {

  @Override
  public String wrap(final String lineToWrap, final int maxCharsPerLine) {
    StringBuilder sb = new StringBuilder(lineToWrap);

    int index = maxCharsPerLine;
    int minIndex = -1;

    while (index > minIndex && index < sb.length()) {
      if (sb.charAt(index) == ' ') {
        sb.setCharAt(index, '\n');
        minIndex = index;
        index += maxCharsPerLine;
      } else {
        index--;
      }
    }

    return sb.toString();
  }
}
