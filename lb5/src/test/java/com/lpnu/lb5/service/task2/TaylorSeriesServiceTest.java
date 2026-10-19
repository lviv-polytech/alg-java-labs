package com.lpnu.lb5.service.task2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.lpnu.common.math.TaylorResult;
import com.lpnu.lb5.ui.task2.Task2Runner;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Task 5.2: Taylor Series for ln(x) (Variant 17)")
class TaylorSeriesServiceTest {

  private final TaylorSeriesService service = new TaylorSeriesService();

  @Nested
  @DisplayName("Normal Cases")
  class NormalCases {

    @Test
    @DisplayName("TC-01: Recurrent term calculation at x=2.0 produces exact sequence")
    void shouldCalculateExactRecurrentTermsAtX2() {
      double a0 = (2.0 - 1.0) / 2.0;
      assertEquals(0.5, a0, 1e-12);

      double a1 = service.nextTerm(2.0, 1, a0);
      assertEquals(0.125, a1, 1e-12);

      double a2 = service.nextTerm(2.0, 2, a1);
      assertEquals(1.0 / 24.0, a2, 1e-12);
    }

    @Test
    @DisplayName("TC-02: Series sum at center x=1.0 equals 0.0 in 1 term")
    void shouldReturnZeroInOneTermAtCenterOne() {
      TaylorResult result = service.calculateSeries(1.0, 1e-7);
      assertEquals(0.0, result.sum(), 1e-12);
      assertEquals(1, result.termsCount());
    }

    @Test
    @DisplayName("TC-03: Series sum at x=2.0 with eps=1e-4 includes terminal term (10 terms)")
    void shouldComputeTenTermsAtX2WithEps1e4() {
      TaylorResult result = service.calculateSeries(2.0, 1e-4);
      assertEquals(10, result.termsCount());
      assertEquals(0.69306485615079365, result.sum(), 1e-12);
      assertEquals(Math.log(2.0), result.sum(), 1e-4);
    }

    @Test
    @DisplayName("TC-04: Series sum at x=0.6 with eps=1e-4 converges in 16 alternating terms")
    void shouldConvergeAtX06WithEps1e4() {
      TaylorResult result = service.calculateSeries(0.6, 1e-4);
      assertEquals(16, result.termsCount());
      assertEquals(-0.51078897, result.sum(), 1e-7);
      assertEquals(Math.log(0.6), result.sum(), 1e-4);
    }

    @Test
    @DisplayName("TC-08: Tabulation on [0.6, 2.0] with dx=0.2 avoids floating-point grid drift")
    void shouldTabulateWithoutGridDrift() {
      List<TaylorTableRow> rows = service.tabulate(0.6, 2.0, 0.2, 1e-4);
      assertEquals(8, rows.size());

      assertEquals(0.6, rows.get(0).x(), 1e-12);
      assertEquals(16, rows.get(0).seriesResult().orElseThrow().termsCount());

      assertEquals(0.8, rows.get(1).x(), 1e-12);
      assertEquals(6, rows.get(1).seriesResult().orElseThrow().termsCount());
      assertEquals(-0.22313639, rows.get(1).seriesResult().orElseThrow().sum(), 1e-7);

      assertEquals(1.0, rows.get(2).x(), 1e-12);
      assertEquals(0.0, rows.get(2).seriesResult().orElseThrow().sum(), 1e-12);
      assertEquals(1, rows.get(2).seriesResult().orElseThrow().termsCount());

      assertEquals(2.0, rows.get(7).x(), 1e-12);
      assertEquals(10, rows.get(7).seriesResult().orElseThrow().termsCount());
      assertEquals(0.69306485615079365, rows.get(7).seriesResult().orElseThrow().sum(), 1e-12);
    }
  }

  @Nested
  @DisplayName("Boundary & Out-of-Domain Cases")
  class BoundaryCases {

    @Test
    @DisplayName("TC-06: Mixed-domain tabulation [0.2, 1.0] marks x<=0.5 as out-of-domain")
    void shouldMarkPointsAtOrBelowHalfAsOutOfDomain() {
      List<TaylorTableRow> rows = service.tabulate(0.2, 1.0, 0.4, 1e-4);
      assertEquals(3, rows.size());

      assertFalse(rows.get(0).seriesResult().isPresent());
      assertTrue(rows.get(0).exactLn().isPresent());

      assertTrue(rows.get(1).seriesResult().isPresent());
      assertEquals(0.6, rows.get(1).x(), 1e-12);

      assertTrue(rows.get(2).seriesResult().isPresent());
      assertEquals(1.0, rows.get(2).x(), 1e-12);
    }

    @Test
    @DisplayName("Table formatter produces fixed-width rows that never break alignment")
    void shouldFormatTableWithConsistentLineWidths() {
      List<TaylorTableRow> rows = service.tabulate(-0.2, 1.0, 0.4, 1e-4);
      String header = Task2Runner.formatTableHeader();
      String[] headerLines = header.split("\\R");
      int expectedWidth = headerLines[0].length();

      for (String line : headerLines) {
        assertEquals(expectedWidth, line.length());
      }
      for (TaylorTableRow row : rows) {
        String formattedRow = Task2Runner.formatTableRow(row);
        assertEquals(expectedWidth, formattedRow.length());
      }
    }
  }

  @Nested
  @DisplayName("Invalid Input Cases")
  class ErrorCases {

    @Test
    @DisplayName("TC-05: Direct series calculation throws when x <= 0.5")
    void shouldThrowWhenXIsAtOrBelowHalf() {
      assertThrows(IllegalArgumentException.class, () -> service.calculateSeries(0.5, 1e-4));
      assertThrows(IllegalArgumentException.class, () -> service.calculateSeries(-1.0, 1e-4));
    }

    @Test
    @DisplayName("TC-07: Tabulation throws on invalid bounds, non-positive dx, or non-positive eps")
    void shouldThrowOnInvalidTabulationParameters() {
      assertThrows(IllegalArgumentException.class, () -> service.tabulate(2.0, 1.0, 0.1, 1e-4));
      assertThrows(IllegalArgumentException.class, () -> service.tabulate(1.0, 2.0, 0.0, 1e-4));
      assertThrows(IllegalArgumentException.class, () -> service.tabulate(1.0, 2.0, -0.5, 1e-4));
      assertThrows(IllegalArgumentException.class, () -> service.tabulate(1.0, 2.0, 0.5, 0.0));
      assertThrows(IllegalArgumentException.class, () -> service.tabulate(1.0, 2.0, 0.5, -1e-4));
    }
  }
}
