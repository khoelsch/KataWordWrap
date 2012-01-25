package com.coremedia.codekata.wordwrap;

/**
 * A nicer LineWrapper implementation by rre
 */
public final class RreLineWrapper3 implements LineWrapper {

  private static final char LINEBREAK = '\n';
  private static final char BLANK = ' ';


  @Override
  public String wrap(final String lineToWrap, final int maxCharsPerLine) {
    if (maxCharsPerLine <= 0 || maxCharsPerLine >= lineToWrap.length()) {
      return lineToWrap;
    }

    final StringBuilder result = new StringBuilder(lineToWrap);

    int from = 1;
    int to = Math.min(maxCharsPerLine, lineToWrap.length() - 2);
    int lineBreakIndex;

    while (to < lineToWrap.length() - 1) {
      // from the index where the line should be split, go backwards looking for the closest 'blank' to the left
      lineBreakIndex = findLastBlank(result, from, to);
      if (lineBreakIndex != -1) {
        result.setCharAt(lineBreakIndex, LINEBREAK);
        from = lineBreakIndex + 2; // no line-break right after the current one, so '+ 2' instead of '+ 1'
        to = from + maxCharsPerLine - 1;
      }
      // if there's no blank in the line look for the closest blank to the right and split there
      else {
        lineBreakIndex = findBlank(result, to + 1);
        if (lineBreakIndex != -1) {
          result.setCharAt(lineBreakIndex, LINEBREAK);
          from = lineBreakIndex + 2; // no line-break right after the current one, so '+ 2' instead of '+ 1'
          to = from + maxCharsPerLine - 1;
        } else {
          break;
        }
      }
    }

    return result.toString();
  }

  private int findBlank(final StringBuilder stringBuilder, final int from) {
    return stringBuilder.indexOf(String.valueOf(BLANK), from);
  }

  private int findLastBlank(final StringBuilder stringBuilder, final int from, final int to) {
    final int blankIndex = stringBuilder.lastIndexOf(String.valueOf(BLANK), to);
    return (blankIndex >= from)
            ? blankIndex
            : -1;
  }
}
