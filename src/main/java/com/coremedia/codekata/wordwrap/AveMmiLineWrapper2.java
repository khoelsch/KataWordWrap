package com.coremedia.codekata.wordwrap;

/**
 * @since 1/26/12
 */
public class AveMmiLineWrapper2 implements LineWrapper {
    public String wrap(String lineToWrap, int maxCharsPerLine) {
        if (maxCharsPerLine >= lineToWrap.length() || maxCharsPerLine < 1) {
            return lineToWrap;
        }
        final StringBuilder stringBuilder = new StringBuilder(lineToWrap.length());
        final String toAnalyze;
        final String currentLine;
        final String remaining;
        final String trimmed = lineToWrap.startsWith(" ") ? lineToWrap.substring(1): lineToWrap;
        toAnalyze = trimmed.substring(0, maxCharsPerLine);
        final int lastSpacePos = toAnalyze.lastIndexOf(' ');
        if (lastSpacePos == -1) {
            currentLine = toAnalyze;
            remaining = trimmed.substring(maxCharsPerLine);
        } else {
            currentLine = toAnalyze.substring(0, lastSpacePos);
            remaining = trimmed.substring(lastSpacePos + 1);
        }
        final String remainingWrapped = wrap(remaining, maxCharsPerLine);
        stringBuilder.append(currentLine);
        stringBuilder.append('\n');
        stringBuilder.append(remainingWrapped);
        return stringBuilder.toString();
    }
}
