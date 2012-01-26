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
public class LastIndexWrapper implements LineWrapper {
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
    int breakPos = nextLineStartPos + maxCharsPerLine;
    if (lineToWrap.charAt(breakPos) == ' ') {
      writeLineExcluding(breakPos);
    } else {
      int rightmostSpacePos = lineToWrap.lastIndexOf(" ", breakPos);
      if (rightmostSpacePos != -1) {
        writeLineExcluding(rightmostSpacePos);
      } else {
        writeLineResumeAt(breakPos);
      }
    }
  }

  private void writeLineExcluding(int breakPos) {
    writeLineUpTo(breakPos);
    nextLineStartPos = breakPos + 1;
  }

  private void writeLineResumeAt(int breakPos) {
    writeLineUpTo(breakPos);
    nextLineStartPos = breakPos;
  }

  private void writeLineUpTo(int breakPos) {
    String line = lineToWrap.substring(nextLineStartPos, breakPos);
    destBuilder.append(line);
    destBuilder.append('\n');
  }

  private void writeRemainingChars() {
    String line = lineToWrap.substring(nextLineStartPos);
    destBuilder.append(line);
    nextLineStartPos = lineToWrap.length();
  }
}
