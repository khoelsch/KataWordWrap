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
        String wordPart = removeLastWordPartAndTrainlingSpace();
        newLineBuilder.append("\n");
        newLineBuilder.append(wordPart);
      }
      newLineBuilder.append(lineToWrap.charAt(i));
    }

    return null;
  }

  private String removeLastWordPartAndTrainlingSpace() {
    int lastCharacterIndex = newLineBuilder.length() - 1;
    int lastSpaceIndex = newLineBuilder.indexOf(" ", lastCharacterIndex);
    int lastWordFirstCharIndex = lastSpaceIndex + 1;
    String wordPart = newLineBuilder.substring(lastWordFirstCharIndex, lastCharacterIndex);
    newLineBuilder.delete(lastSpaceIndex, lastCharacterIndex);
    return wordPart;
  }
}
