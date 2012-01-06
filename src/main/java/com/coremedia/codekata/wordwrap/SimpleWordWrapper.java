package com.coremedia.codekata.wordwrap;

/**
 * The first simple, straight-forward approach.
 */
public class SimpleWordWrapper implements WordWrapper {
  private int maxCharsPerLine;
  private StringBuilder newLineBuilder;

  public String wrap(String lineToWrap, int maxCharsPerLine) {
    this.maxCharsPerLine = maxCharsPerLine;

    return (lineToWrap.length() <= maxCharsPerLine)
      ? lineToWrap
      : wrapLine(lineToWrap);
  }

  private String wrapLine(String lineToWrap) {
    String[] words = lineToWrap.split(" ");
    String wrappedLine = fillLines(words);
    return wrappedLine.trim();
  }

  private String fillLines(String[] words) {
    newLineBuilder = new StringBuilder();
    int currentLineLength = 0;

    for (String word : words) {
      String wordWithTrainlingSpace = word + " ";
      boolean spaceLeftInLine = (currentLineLength + wordWithTrainlingSpace.length()) <= maxCharsPerLine;
      if (spaceLeftInLine) {
        newLineBuilder.append(wordWithTrainlingSpace);
        currentLineLength += wordWithTrainlingSpace.length();
      } else {
        removeTralingSpaceAndAddLineBreak();
        newLineBuilder.append(wordWithTrainlingSpace);
        currentLineLength = wordWithTrainlingSpace.length();
      }
    }

    return newLineBuilder.toString();
  }

  private void removeTralingSpaceAndAddLineBreak() {
    newLineBuilder.deleteCharAt(newLineBuilder.length() - 1);
    newLineBuilder.append("\n");
  }
}
