package com.coremedia.codekata.wordwrap.performance;

import com.coremedia.codekata.wordwrap.SimpleWordWrapper;

public class WordWrapPerformanceMeter {
  final static int MAX_CHARS_PER_LINE = 10;
  final static String LINE = "I am such a big line you can't even imaging how damn big I am. " +
          "You should really consider telling your local newspaper!";
  private static SimpleWordWrapper wrapper;

  public static void main(String[] args) {
    int runs = 100;
    long startTime = System.currentTimeMillis();

    for (int i=0; i<runs; ++i) {
      wrapper = new SimpleWordWrapper();
      wrapper.wrap(LINE, MAX_CHARS_PER_LINE);
    }

    long endTime = System.currentTimeMillis();
    long totalTime = endTime - startTime;

    double totalSeconds = (double) totalTime / 1000;
    double average = (double) totalTime / runs;
    System.out.println(String.format("Average duration: %.3g Milliseconds (with %d runs and a total of %.3g seconds)", average, runs, totalSeconds));
  }
}
