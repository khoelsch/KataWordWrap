package com.coremedia.codekata.wordwrap;

/**
 * <p>This implementation utilizes String.lastIndexOf().</p>
 *
 * <p>The idea is not to break a word, if you can find a
 * space on the left. Example:</p>
 *
 * <ul>
 *   <li>maxCharsPerLine = 6</li>
 *   <li>Input: "foo bar"</li>
 *   <li>Output: "foo\\nbar" (and NOT "foo b\\nar")</li>
 * </ul>
 *
 * <p>Note: This class' methods are written in "Clean Code" style, as stated by Robert C. Martin (aka Uncle Bob).</p>
 */
public class LastIndexOfWrapper implements LineWrapper {
  private StringBuilder srcBuilder;
  private int maxCharsPerLine;
  private StringBuilder destBuilder;
  private int nextLineStartPos;

  @Override
  public String wrap(String lineToWrap, int maxCharsPerLine) {
    init(lineToWrap, maxCharsPerLine);

    return lineTooLong() ? wrapLine() : lineToWrap;
  }

  private void init(String lineToWrap, int maxCharsPerLine) {
    srcBuilder = new StringBuilder(lineToWrap);
    this.maxCharsPerLine = maxCharsPerLine;
    destBuilder = new StringBuilder();
    nextLineStartPos = 0;
  }

  private boolean lineTooLong() {
    return srcBuilder.length() > maxCharsPerLine;
  }

  private String wrapLine() {
    while (charsLeftToConsume()) {
      writeCharsToBuffer();
    }

    return destBuilder.toString();
  }

  private boolean charsLeftToConsume() {
    return nextLineStartPos < srcBuilder.length();
  }

  private void writeCharsToBuffer() {
    if (nextBreakpointInLine()) {
      writeNextLine();
    } else {
      writeRemainingChars();
    }
  }

  private boolean nextBreakpointInLine() {
    return (nextLineStartPos + maxCharsPerLine) < srcBuilder.length();
  }

  private void writeNextLine() {
    int posToInsertNewLine = nextLineStartPos + maxCharsPerLine;
    if (srcBuilder.charAt(posToInsertNewLine) == ' ') {
      writeLineSkip(posToInsertNewLine);
    } else {
      int rightmostSpacePos = srcBuilder.lastIndexOf(" ", posToInsertNewLine);
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
    for (int i=nextLineStartPos; i<posToInsertNewLine; ++i) {
      destBuilder.append(srcBuilder.charAt(i));
    }

    destBuilder.append('\n');
  }

  private void writeRemainingChars() {
    String line = srcBuilder.substring(nextLineStartPos);
    destBuilder.append(line);
    nextLineStartPos = srcBuilder.length();
  }
}
