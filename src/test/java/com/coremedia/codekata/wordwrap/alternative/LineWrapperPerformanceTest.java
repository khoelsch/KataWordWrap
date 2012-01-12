package com.coremedia.codekata.wordwrap.alternative;

import com.coremedia.codekata.wordwrap.CharArrayLineWrapper;
import com.coremedia.codekata.wordwrap.CharAtLineWrapper;
import com.coremedia.codekata.wordwrap.LineWrapper;
import com.coremedia.codekata.wordwrap.RreLineWrapper;
import com.coremedia.codekata.wordwrap.RreLineWrapper2;
import com.coremedia.codekata.wordwrap.SplitLineWrapper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * A different approach: Performance test as data-driven UnitTest
 */
@RunWith(Parameterized.class)
public final class LineWrapperPerformanceTest {

  protected final LineWrapper wrapper;

  public static final String TEST_TXT = "ipsum.txt";
  public static final int MAX_CHARS_PER_LINE = 20;
  private static final List<String> listOfLines = new ArrayList<String>();

  public LineWrapperPerformanceTest(final LineWrapper wrapper) {
    this.wrapper = wrapper;
  }

  @Parameterized.Parameters
  public static Collection<Object[]> data() {
    Collection<Object[]> wrappers = new ArrayList<Object[]>();

    wrappers.add(new LineWrapper[]{new SplitLineWrapper()});
    wrappers.add(new LineWrapper[]{new RreLineWrapper()});
    wrappers.add(new LineWrapper[]{new RreLineWrapper2()});
    wrappers.add(new LineWrapper[]{new CharAtLineWrapper()});
    wrappers.add(new LineWrapper[]{new CharArrayLineWrapper()});

    return wrappers;
  }

  @BeforeClass
  public static void setUp() throws Exception {
    readTestText();
  }

  @Test
  public void testPerformance() {
    long startTime = System.nanoTime();
    for (String line : listOfLines) {
      wrapper.wrap(line, MAX_CHARS_PER_LINE);
    }
    long finishTime = System.nanoTime();

    long duration = TimeUnit.NANOSECONDS.toMicros(finishTime - startTime);
    System.out.println(wrapper.getClass().getSimpleName() + " took " + duration + " microseconds");
  }

  private static void readTestText() throws IOException {
    final BufferedReader reader = new BufferedReader(new InputStreamReader(
            LineWrapperPerformanceTest.class.getClassLoader().getResourceAsStream(TEST_TXT)));

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
