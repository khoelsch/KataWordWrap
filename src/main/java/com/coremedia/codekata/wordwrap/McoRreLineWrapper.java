package com.coremedia.codekata.wordwrap;

/**
 * TODO: add JavaDoc
 */
public class McoRreLineWrapper implements LineWrapper {
  public String wrap(final String lineToWrap, final int maxCharsPerLine) {
    if (lineToWrap.length() <= maxCharsPerLine || maxCharsPerLine <= 0) {
      return lineToWrap;
    }
    StringBuilder lineToWrapBuilder = new StringBuilder(lineToWrap);
    int currentPosition = -1;
    int lastWrapPosition = -1;
    int lastFoundBlankPosition = -1;
    while ((currentPosition = lineToWrapBuilder.indexOf(" ", currentPosition+1)) != -1) {

      if (currentPosition - lastWrapPosition > maxCharsPerLine) {
        if (lastFoundBlankPosition != lastWrapPosition) {
          lineToWrapBuilder.setCharAt(lastFoundBlankPosition, '\n');
          lastWrapPosition = lastFoundBlankPosition;
        } else {
          lineToWrapBuilder.setCharAt(currentPosition, '\n');
          lastWrapPosition = currentPosition;
        }
      }

      lastFoundBlankPosition = currentPosition;
    }
    if (lineToWrap.length() - (lastWrapPosition+1) > maxCharsPerLine && lastFoundBlankPosition != lastWrapPosition) {
      lineToWrapBuilder.setCharAt(lastFoundBlankPosition, '\n');
    }
    return lineToWrapBuilder.toString();
  }

}
