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
      writeLineToBuffer();
    }

    return destBuilder.toString();
  }

  private void writeLineToBuffer() {
    if (nextBreakpointInLine()) {
      int breakPos = nextLineStartPos + maxCharsPerLine;
      char charAtBreakPos = lineToWrap.charAt(breakPos);
      if (charAtBreakPos == ' ') {
        String line = lineToWrap.substring(nextLineStartPos, breakPos);
        destBuilder.append(line);
        destBuilder.append('\n');
        nextLineStartPos = breakPos + 1;
      } else {
        // TODO berueksichtigen, wenn der String keine Leerzeichen enthaelt!
        int rightmostSpacePos = lineToWrap.lastIndexOf(" ", breakPos);
        String line = lineToWrap.substring(nextLineStartPos, rightmostSpacePos);
        destBuilder.append(line);
        destBuilder.append('\n');
        nextLineStartPos = rightmostSpacePos + 1;
      }
    } else {
      String line = lineToWrap.substring(nextLineStartPos);
      destBuilder.append(line);
      nextLineStartPos = lineToWrap.length();
    }
  }

  private boolean nextBreakpointInLine() {
    return (nextLineStartPos + maxCharsPerLine) <= lineToWrap.length();
  }

  private boolean charsLeftToConsume() {
    return nextLineStartPos < lineToWrap.length();
  }
}
