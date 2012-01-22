package com.coremedia.codekata.wordwrap;

/**
 * A faster LineWrapper implementation by rre
 */
public final class RreLineWrapper2 implements LineWrapper {

  @Override
  public String wrap(final String lineToWrap, final int maxCharsPerLine) {
    if (maxCharsPerLine <= 0) {
      return lineToWrap;
    }

    StringBuilder sb = new StringBuilder(lineToWrap);

    int index = maxCharsPerLine;
    int minIndex = 1;
    boolean overflow = false;

    while (index < sb.length()) {
      if (sb.charAt(index) == ' ') {
        sb.setCharAt(index, '\n');
        minIndex = index + 2;
        index += maxCharsPerLine + 1;
        overflow = false;
      } else {
        if (index > minIndex) {
          if (overflow) {
            index++;
          } else {
            index--;
          }
        } else {
          index += maxCharsPerLine;
          overflow = true;
        }
      }
    }

    return sb.toString();
  }
}
