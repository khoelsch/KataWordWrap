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

  @Override
  public String wrap(String lineToWrap, int maxCharsPerLine) {
    init(lineToWrap, maxCharsPerLine);

    return lineTooLong() ? wrapLine() : lineToWrap;
  }

  private void init(String lineToWrap, int maxCharsPerLine) {
    this.lineToWrap = lineToWrap;
    this.maxCharsPerLine = maxCharsPerLine;
    this.destBuilder = new StringBuilder();
  }

  private boolean lineTooLong() {
    return lineToWrap.length() > maxCharsPerLine;
  }

  private String wrapLine() {
    int breakPos = lineToWrap.lastIndexOf(" ", maxCharsPerLine);
    if (breakPos > -1) {
      destBuilder.append(lineToWrap.substring(0,breakPos));
      destBuilder.append("\n");
      destBuilder.append(lineToWrap.substring(breakPos+1));
   }
   return destBuilder.toString();
  }
}
