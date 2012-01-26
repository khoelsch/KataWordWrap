package com.coremedia.codekata.wordwrap;

/**
 * Utilizes String.lastIndexOf().
 *
 * The idea is not to break a word, if you can find a
 * space on the left.
 */
public class LastIndexWrapper implements LineWrapper {
  @Override
  public String wrap(String lineToWrap, int maxCharsPerLine) {
    String result = lineToWrap;
    StringBuilder destBuilder = new StringBuilder();

    if (lineToWrap.length() > maxCharsPerLine) {

      final int breakpos = lineToWrap.lastIndexOf(" ", maxCharsPerLine);
      if (breakpos > -1) {
        destBuilder.append(lineToWrap.substring(0,breakpos));
        destBuilder.append("\n");
        destBuilder.append(lineToWrap.substring(breakpos+1));
     }
      result = destBuilder.toString();
    }

    /*
    maxchlength = 3
    --
    ab cd  => ab\ncd
    ab  cd => ab\ncd
    abc => abc


    maxCharsPerLength = 4
    ab cd => ab c\nd


    */
    return result;
  }
}
