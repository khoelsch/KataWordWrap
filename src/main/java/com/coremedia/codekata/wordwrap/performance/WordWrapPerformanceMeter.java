package com.coremedia.codekata.wordwrap.performance;

import com.coremedia.codekata.wordwrap.SplitLineWrapper;

public class WordWrapPerformanceMeter {
  private static SplitLineWrapper wrapper;
  private static final int RUNS = 100;
  final static int MAX_CHARS_PER_LINE = 10;
  final static String LINE = "I am such a big line you can't even imaging how damn big I am. " +
          "You should really consider telling your local newspaper!";

  public static void main(String[] args) {
    long startTime = System.nanoTime();

    for (int i=0; i< RUNS; ++i) {
      wrapper = new SplitLineWrapper();
      wrapper.wrap(LINE, MAX_CHARS_PER_LINE);
    }

    long endTime = System.nanoTime();
    long totalTime = endTime - startTime;

    double totalMillis = (double) totalTime / (1000*1000);
    double averageMillis = totalMillis / RUNS;
    System.out.println(String.format("Average duration: %.3g Milliseconds (with %d runs and a total of %.3g Milliseconds)",
            averageMillis,
            RUNS,
            totalMillis));
  }
}
