package com.coremedia.codekata.wordwrap;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * A different approach: Performance test as data-driven UnitTest
 */
@RunWith(Parameterized.class)
public final class LineWrapperPerformanceTest extends DataDrivenLineWrapperTest {

  public static final int MAX_CHARS_PER_LINE = 20;
  private static final List<String> testText = new ArrayList<String>();

  public LineWrapperPerformanceTest(final LineWrapper wrapper) {
    super(wrapper);
  }

  @BeforeClass
  public static void setUp() throws Exception {
    final BufferedReader reader = new BufferedReader(new InputStreamReader(LineWrapperPerformanceTest.class.getClassLoader().getResourceAsStream("ipsum.txt")));

    try {
      String line;
      while ((line = reader.readLine()) != null) {
        testText.add(line);
      }
    } finally {
      reader.close();
    }
  }

  @Test
  public void testPerformance() {
    long startTime = System.nanoTime();
    for (String line : testText) {
      wrapper.wrap(line, MAX_CHARS_PER_LINE);
    }
    long finishTime = System.nanoTime();

    long duration = TimeUnit.NANOSECONDS.toMicros(finishTime - startTime);
    System.out.println(wrapper.getClass().getSimpleName() + " took " + duration + " microseconds");
  }

}
