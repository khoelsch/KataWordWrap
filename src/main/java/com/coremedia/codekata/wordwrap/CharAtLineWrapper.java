package com.coremedia.codekata.wordwrap;

/**
 * Wraps line bei iterating over the line's characters.
 */
public class CharAtLineWrapper implements LineWrapper {
  private int maxCharsPerLine;
  private StringBuilder newLineBuilder = new StringBuilder();

  public String wrap(String lineToWrap, int maxCharsPerLine) {
    this.maxCharsPerLine = maxCharsPerLine;

    return (lineToWrap.length() <= maxCharsPerLine)
      ? lineToWrap
      : wrapLine(lineToWrap);
  }

  private String wrapLine(String lineToWrap) {
    int currentLineLength = 0;
    for (int i=0;
         i < lineToWrap.length();
         ++i) {
      boolean noSpaceLeftInLine = (currentLineLength+1) > maxCharsPerLine;
      if (noSpaceLeftInLine) {
        String wordPart = removeLastWordPartAndTrailingSpace();
        newLineBuilder.append("\n");
        currentLineLength = 0;
        newLineBuilder.append(wordPart);
      }
      newLineBuilder.append(lineToWrap.charAt(i));
      ++currentLineLength;
    }

    return newLineBuilder.toString();
  }

  /**
   * @return the last word without(!) leading space
   */
  private String removeLastWordPartAndTrailingSpace() {
    int lastCharIndex = newLineBuilder.length() - 1;
    int lastSpaceIndex = newLineBuilder.lastIndexOf(" ");
    int firstCharOfLastWordIndex = lastSpaceIndex + 1;
    String wordPart = newLineBuilder.substring(firstCharOfLastWordIndex, lastCharIndex + 1);
    newLineBuilder.delete(lastSpaceIndex, lastCharIndex + 1);
    return wordPart;
  }
}
