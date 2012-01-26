package com.coremedia.codekata.wordwrap;

/**
 * <p>Utilizes String.lastIndexOf().</p>
 *
 * <p>The idea is not to break a word, if you can find a
 * space on the left. Example:</p>
 *
 * <ul>
 *   <li>maxCharsPerLine = 6</li>
 *   <li>Input: "foo bar"</li>
 *   <li>Output: "foo\\nbar" (and NOT "foo b\\nar")</li>
 * </ul>
 */
public class LastIndexOfWrapper implements LineWrapper {
  private String lineToWrap;
  private int maxCharsPerLine;
  private StringBuilder destBuilder;
  private int nextLineStartPos;

  @Override
  public String wrap(String lineToWrap, int maxCharsPerLine) {
    init(lineToWrap, maxCharsPerLine);

    return lineTooLong() ? wrapLine() : lineToWrap;
  }

  private void init(String lineToWrap, int maxCharsPerLine) {
    this.lineToWrap = lineToWrap;
    this.maxCharsPerLine = maxCharsPerLine;
    destBuilder = new StringBuilder();
    nextLineStartPos = 0;
  }

  private boolean lineTooLong() {
    return lineToWrap.length() > maxCharsPerLine;
  }

  private String wrapLine() {
    while (charsLeftToConsume()) {
      writeCharsToBuffer();
    }

    return destBuilder.toString();
  }

  private boolean charsLeftToConsume() {
    return nextLineStartPos < lineToWrap.length();
  }

  private void writeCharsToBuffer() {
    if (nextBreakpointInLine()) {
      writeNextLine();
    } else {
      writeRemainingChars();
    }
  }

  private boolean nextBreakpointInLine() {
    return (nextLineStartPos + maxCharsPerLine) < lineToWrap.length();
  }

  private void writeNextLine() {
    int posToInsertNewLine = nextLineStartPos + maxCharsPerLine;
    if (lineToWrap.charAt(posToInsertNewLine) == ' ') {
      writeLineSkip(posToInsertNewLine);
    } else {
      int rightmostSpacePos = lineToWrap.lastIndexOf(" ", posToInsertNewLine);
      if (rightmostSpacePos != -1) {
        writeLineSkip(rightmostSpacePos);
      } else {
        writeLineResumeAt(posToInsertNewLine);
      }
    }
  }

  private void writeLineSkip(int posToInsertNewLine) {
    writeLineExcluding(posToInsertNewLine);
    nextLineStartPos = posToInsertNewLine + 1;
  }

  private void writeLineResumeAt(int posToInsertNewLine) {
    writeLineExcluding(posToInsertNewLine);
    nextLineStartPos = posToInsertNewLine;
  }

  private void writeLineExcluding(int posToInsertNewLine) {
    String line = lineToWrap.substring(nextLineStartPos, posToInsertNewLine);
    destBuilder.append(line);
    destBuilder.append('\n');
  }

  private void writeRemainingChars() {
    String line = lineToWrap.substring(nextLineStartPos);
    destBuilder.append(line);
    nextLineStartPos = lineToWrap.length();
  }
}
