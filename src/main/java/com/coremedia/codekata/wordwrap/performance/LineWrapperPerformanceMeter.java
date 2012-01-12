package com.coremedia.codekata.wordwrap.performance;

import com.coremedia.codekata.wordwrap.CharAtLineWrapper;
import com.coremedia.codekata.wordwrap.LineWrapper;
import com.coremedia.codekata.wordwrap.SplitLineWrapper;

import java.util.ArrayList;
import java.util.List;

public class LineWrapperPerformanceMeter {
  private static final int RUNS = 1000;
  private static String LINE = "I am such a big line you can't even imagine how damn big I am. " +
          "You should really consider telling your local newspaper!";
  private static int MAX_CHARS_PER_LINE = 10;

  private static List<LineWrapper> testedWrappers = new ArrayList<LineWrapper>();
  static {
    testedWrappers.add(new SplitLineWrapper());
    testedWrappers.add(new CharAtLineWrapper());
  }

  public static void main(String[] args) {
    for (LineWrapper wrapper : testedWrappers) {
      printAverageExecutionTime(wrapper);
    }
  }

  private static void printAverageExecutionTime(LineWrapper wrapper) {
    long startTime = System.nanoTime();

    for (int i=0; i< RUNS; ++i) {
      wrapper.wrap(LINE, MAX_CHARS_PER_LINE);
    }

    long endTime = System.nanoTime();
    long totalTime = endTime - startTime;

    double totalMillis = (double) totalTime / (1000*1000);
    double averageMillis = totalMillis / RUNS;
    System.out.println(String.format("Average duration for class '%s': %.3g Milliseconds (with %d runs and a total of %.3g Milliseconds)",
            wrapper.getClass().getName(),
            averageMillis,
            RUNS,
            totalMillis));
  }
}
