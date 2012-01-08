package com.coremedia.codekata.wordwrap;

/**
 * LineWrapper implementation by rre
 */
public final class RreLineWrapper implements LineWrapper {

  @Override
  public String wrap(final String lineToWrap, final int maxCharsPerLine) {
    final StringBuilder result = new StringBuilder();
    final StringBuilder rest = new StringBuilder(lineToWrap);

    while (rest.length() > maxCharsPerLine) {
      final int maxIndex = Math.min(rest.length(), maxCharsPerLine + 1);
      StringBuilder line = new StringBuilder(rest.substring(0, maxIndex));
      final int lastBlankIndex = line.lastIndexOf(" ");

      // if the char at position 'maxCharsPerLine' + 1 is a blank,
      // we already have our line with exactly 'maxCharsPerLine' characters
      if (lastBlankIndex == line.length() - 1) {
        line.deleteCharAt(line.length() - 1);
        result.append(line);
      }
      // else look after the last blank which will be the end of our line
      else {
        line.delete(lastBlankIndex, line.length());
      }

      // delete the current line from 'rest'
      // (second param +1 because delete's second param is excluded from selection
      // and we want to delete the first blank of the next line too)
      rest.delete(0, line.length() + 1);

      result.append(line);
      result.append("\n");

    }

    result.append(rest);

    return result.toString();
  }
}
