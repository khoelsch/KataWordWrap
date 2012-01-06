package com.coremedia.codekata.wordwrap;

/**
 * The first simple, straight-forward approach.
 */
public class SimpleWordWrapper implements WordWrapper {

  public String wrap(String lineToWrap, int maxCharsPerLine) {
    return (lineToWrap.length() <= maxCharsPerLine)
      ? lineToWrap
      : wrapLine(lineToWrap, maxCharsPerLine);
  }

  private String wrapLine(String lineToWrap, int maxCharsPerLine) {
    StringBuilder newLineBuilder = new StringBuilder();

    String[] words = lineToWrap.split(" ");

    int lineLength = 0;
    for (String word : words) {
      String wordWithTrainlingSpace = word + " ";
      boolean spaceLeftInLine = (lineLength + wordWithTrainlingSpace.length()) <= maxCharsPerLine;
      if (spaceLeftInLine) {
        newLineBuilder.append(wordWithTrainlingSpace);
        lineLength += wordWithTrainlingSpace.length();
      } else {
        newLineBuilder.deleteCharAt(newLineBuilder.length()-1);
        newLineBuilder.append("\n");
        newLineBuilder.append(wordWithTrainlingSpace);
        lineLength = wordWithTrainlingSpace.length();
      }
    }

    String wrappedLine = newLineBuilder.toString();
    return wrappedLine.trim();
  }
}
