package com.coremedia.codekata.wordwrap;

/**
 * Wraps line bei iterating over the line's characters.
 */
public class CharArrayLineWrapper implements LineWrapper {
  private int maxCharsPerLine;
  private int currentLineLength;
  private StringBuilder newLineBuilder;

  public String wrap(String lineToWrap, int maxCharsPerLine) {
    this.maxCharsPerLine = maxCharsPerLine;

    return (lineToWrap.length() <= maxCharsPerLine)
      ? lineToWrap
      : wrapLine(lineToWrap);
  }

  private String wrapLine(String lineToWrap) {
    currentLineLength = 0;
    newLineBuilder = new StringBuilder();

    char[] lineChars = lineToWrap.toCharArray();
    for (int i=0;
         i < lineChars.length;
         ++i) {
      if (noSpaceLeftInLine()) {
        String wordPart = removeLastWordPartAndTrailingSpace();
        breakLine();
        newLineBuilder.append(wordPart);
      }
      appendChar(lineChars[i]);
    }

    return newLineBuilder.toString();
  }

  private boolean noSpaceLeftInLine() {
    return (currentLineLength+1) > maxCharsPerLine;
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

  private void appendChar(char foo) {
    newLineBuilder.append(foo);
    ++currentLineLength;
  }

  private void breakLine() {
    newLineBuilder.append("\n");
    currentLineLength = 0;
  }
}
