package com.coremedia.codekata.wordwrap.benchmark;

import com.carrotsearch.junitbenchmarks.AbstractBenchmark;
import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.annotation.AxisRange;
import com.carrotsearch.junitbenchmarks.annotation.BenchmarkHistoryChart;
import com.carrotsearch.junitbenchmarks.annotation.BenchmarkMethodChart;
import com.carrotsearch.junitbenchmarks.annotation.LabelType;
import com.coremedia.codekata.wordwrap.CharArrayLineWrapper;
import com.coremedia.codekata.wordwrap.CharAtLineWrapper;
import com.coremedia.codekata.wordwrap.IndexAccessLineWrapper;
import com.coremedia.codekata.wordwrap.LineWrapper;
import com.coremedia.codekata.wordwrap.McoRreLineWrapper;
import com.coremedia.codekata.wordwrap.RreLineWrapper;
import com.coremedia.codekata.wordwrap.RreLineWrapper2;
import com.coremedia.codekata.wordwrap.RreLineWrapper3;
import com.coremedia.codekata.wordwrap.SplitLineWrapper;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Benchmark for the different {@link com.coremedia.codekata.wordwrap.LineWrapper} implementations.
 */
@AxisRange(min = 0, max = 1)
@BenchmarkMethodChart(filePrefix = "LineWrapper-Benchmark")
@BenchmarkHistoryChart(labelWith = LabelType.TIMESTAMP, maxRuns = 20, filePrefix = "LineWrapper-Benchmark-History")
public class LineWrapperBenchmarkTest extends AbstractBenchmark {

  private static final String TEST_TXT = "ipsum.txt";
  private static final int MAX_CHARS_PER_LINE = 20;
  private static final List<String> listOfLines = new ArrayList<String>();
  private static final Map<String, LineWrapper> wrappers = new HashMap<String, LineWrapper>();

  @BeforeClass
  public static void setUpClass() throws Exception {
    readTestText();

    wrappers.put("SplitLineWrapper", new SplitLineWrapper());
    wrappers.put("RreLineWrapper", new RreLineWrapper());
    wrappers.put("RreLineWrapper2", new RreLineWrapper2());
    wrappers.put("RreLineWrapper3", new RreLineWrapper3());
    wrappers.put("IndexAccessLineWrapper", new IndexAccessLineWrapper());
    wrappers.put("CharAtLineWrapper", new CharAtLineWrapper());
    wrappers.put("CharArrayLineWrapper", new CharArrayLineWrapper());
    wrappers.put("McoRreLineWrapper", new McoRreLineWrapper());
  }

  @Test
  @BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 10, callgc = true)
  public void testSplitLineWrapper() throws Exception {
    testPerformance(wrappers.get("SplitLineWrapper"));
  }

  @Test
  @BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 10, callgc = true)
  public void testRreLineWrapper() throws Exception {
    testPerformance(wrappers.get("RreLineWrapper"));

  }

  @Test
  @BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 10, callgc = true)
  public void testRreLineWrapper2() throws Exception {
    testPerformance(wrappers.get("RreLineWrapper2"));
  }

 @Test
  @BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 10, callgc = true)
  public void testRreLineWrapper3() throws Exception {
    testPerformance(wrappers.get("RreLineWrapper3"));
  }

  @Test
  @BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 10, callgc = true)
  public void testCharArrayLineWrapper() throws Exception {
    testPerformance(wrappers.get("CharArrayLineWrapper"));
  }

  @Test
  @BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 10, callgc = true)
  public void testCharAtLineWrapper() throws Exception {
    testPerformance(wrappers.get("CharAtLineWrapper"));
  }

  @Test
  @BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 10, callgc = true)
  public void testIndexAccessLineWrapper() throws Exception {
    testPerformance(wrappers.get("IndexAccessLineWrapper"));
  }

  @Test
  @BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 10, callgc = true)
  public void testMcoRreLineWrapper() throws Exception {
    testPerformance(wrappers.get("McoRreLineWrapper"));
  }

  private void testPerformance(final LineWrapper wrapper) {
    for (int i = 0; i < 100; i++) {
      for (final String line : listOfLines) {
        wrapper.wrap(line, MAX_CHARS_PER_LINE);
      }
    }
  }

  private static void readTestText() throws IOException {
    final BufferedReader reader = new BufferedReader(new InputStreamReader(
            LineWrapperBenchmarkTest.class.getClassLoader().getResourceAsStream(TEST_TXT)));

    try {
      String line;
      while ((line = reader.readLine()) != null) {
        listOfLines.add(line);
      }
    } finally {
      reader.close();
    }
  }

}
