package com.coremedia.codekata.wordwrap;

/**
 * <p>This implementation utilizes String.lastIndexOf().</p>
 *
 * <p>The idea is not to break a word, if you can find a
 * space on the left. Example:</p>
 *
 * <ul>
 *   <li>maxCharsPerLine = 5</li>
 *   <li>Input: "foo bar"</li>
 *   <li>Output: "foo\\nbar" (and NOT "foo b\\nar")</li>
 * </ul>
 *
 * <p>Note: This class' methods are written in "Clean Code" style, as stated by Robert C. Martin (aka Uncle Bob).</p>
 */
public class LastIndexOfWrapper implements LineWrapper {
  private static final String SPACE = " ";
  private StringBuilder srcBuilder;
  private int maxCharsPerLine;
  private StringBuilder destBuilder;
  private int nextLineStartPos;

  @Override
  public String wrap(String lineToWrap, int maxCharsPerLine) {
    init(lineToWrap, maxCharsPerLine);

    boolean lineTooLong = srcBuilder.length() > maxCharsPerLine;
    if (lineTooLong) {
      wrapLine();
      return destBuilder.toString();
    } else {
      return lineToWrap;
    }
  }

  private void init(String lineToWrap, int maxCharsPerLine) {
    srcBuilder = new StringBuilder(lineToWrap);
    this.maxCharsPerLine = maxCharsPerLine;
    destBuilder = new StringBuilder();
    nextLineStartPos = 0;
  }

  private void wrapLine() {
    while (nextLineStartPos < srcBuilder.length()) {
      writeLineToBuffer();
    }
  }

  private void writeLineToBuffer() {
    boolean needToWrap = (nextLineStartPos + maxCharsPerLine) < srcBuilder.length();
    if (needToWrap) {
      writeNextLine();
    } else {
      writeRemainingChars();
    }
  }

  private void writeNextLine() {
    int lastPossibleBreakPos = (nextLineStartPos + maxCharsPerLine);
    boolean spaceAtBreakPos = SPACE.charAt(0) == srcBuilder.charAt(lastPossibleBreakPos);
    if (spaceAtBreakPos) {
      writeLineSkip(lastPossibleBreakPos);
    } else {
      int rightmostSpacePos = srcBuilder.lastIndexOf(SPACE, lastPossibleBreakPos);
      if (rightmostSpacePos != -1) {
        writeLineSkip(rightmostSpacePos);
      } else {
        writeLineResumeAt(lastPossibleBreakPos);
      }
    }
  }


  private void writeLineSkip(int position) {
    writeLineExcluding(position);
    nextLineStartPos = position + 1;
  }

  private void writeLineResumeAt(int position) {
    writeLineExcluding(position);
    nextLineStartPos = position;
  }

  private void writeLineExcluding(int position) {
    for (int i=nextLineStartPos; i<position; ++i) {
      destBuilder.append(srcBuilder.charAt(i));
    }

    destBuilder.append('\n');
  }

  private void writeRemainingChars() {
    String remainder = srcBuilder.substring(nextLineStartPos);
    destBuilder.append(remainder);
    nextLineStartPos = srcBuilder.length();
  }
}
