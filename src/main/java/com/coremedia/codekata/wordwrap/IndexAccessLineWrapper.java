package com.coremedia.codekata.wordwrap;

/**
 * LineWrapper implementation by rre
 */
public final class IndexAccessLineWrapper implements LineWrapper {

  @Override
  public String wrap(final String lineToWrap, final int maxCharsPerLine) {
    if (maxCharsPerLine <= 0) {
      return lineToWrap;
    }

    StringBuilder newLineBuilder = new StringBuilder(lineToWrap);

    int currentIndex = maxCharsPerLine;
    int leftmostCharToCheckIndex = 1;
    boolean noSpacesInCurrentLine = false;

    boolean withinLine = currentIndex < newLineBuilder.length();
    while (withinLine) {
      boolean charIsSpace = newLineBuilder.charAt(currentIndex) == ' ';
      if (charIsSpace) {
        newLineBuilder.setCharAt(currentIndex, '\n');
        leftmostCharToCheckIndex = currentIndex + 2;
        currentIndex += maxCharsPerLine + 1;
        noSpacesInCurrentLine = false;
      } else {
        boolean charsLeftToScan = currentIndex > leftmostCharToCheckIndex;
        if (charsLeftToScan) {
          if (noSpacesInCurrentLine) {
            currentIndex++;
          } else {
            currentIndex--;
          }
        } else {
          currentIndex += maxCharsPerLine;
          noSpacesInCurrentLine = true;
        }
      }
      withinLine = currentIndex < newLineBuilder.length();
    }

    return newLineBuilder.toString();
  }
}
