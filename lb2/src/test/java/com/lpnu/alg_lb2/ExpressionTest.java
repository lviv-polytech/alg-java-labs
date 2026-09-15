package com.lpnu.alg_lb2;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.lpnu.alg_lb2.dto.InParams;
import com.lpnu.alg_lb2.var.SevenTeen;
import java.math.BigDecimal;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * Test class for validating the mathematical expressions defined in Variant 17. It ensures that
 * both formulas (z1 and z2) yield mathematically equivalent results within an acceptable margin of
 * error.
 */
class ExpressionTest {

  private final Variant v_17 = new SevenTeen();
  private final Random random = new Random();

  /**
   * Tests the equality of expressions z1 and z2 for Variant 17.
   *
   * <p>To avoid absolute value sign changes during root extraction, the generated input parameter
   * {@code m} is strictly constrained to be greater than 2/3. Specifically, it is generated in the
   * range [1.0, 2.0).
   *
   * <p>Because BigDecimal calculations (such as square roots and division) introduce microscopic
   * precision losses, the results are compared using an {@code epsilon} threshold rather than
   * strict equality.
   *
   * @throws org.opentest4j.AssertionFailedError if the absolute difference between z1 and z2
   *     exceeds the defined epsilon
   */
  @Test
  void calc_v_17_test() {
    double m_val = 1.0 + random.nextDouble();
    var m = BigDecimal.valueOf(m_val);

    var params = new InParams(m, BigDecimal.ZERO);

    var rs = v_17.expression(params);

    BigDecimal epsilon = new BigDecimal("0.0000000001");

    BigDecimal difference = rs.z1().subtract(rs.z2()).abs();

    System.out.println("=== Generated Inputs ===%n m = %s%n".formatted(m));
    System.out.println(
        "=== Calculated Outputs ===%n z1 = %s%n z2 = %s%n".formatted(rs.z1(), rs.z2()));
    System.out.println("Difference: " + difference);

    assertTrue(
        difference.compareTo(epsilon) < 0,
        "The difference between z1 and z2 exceeds the acceptable epsilon threshold.");
  }

  @Test
  void calc_v_17_happy_path_for_m_equal_to_2() {
    var m = BigDecimal.valueOf(2.0);
    var params = new InParams(m, BigDecimal.ZERO);

    var rs = v_17.expression(params);
    BigDecimal epsilon = new BigDecimal("0.0000000001");
    BigDecimal difference = rs.z1().subtract(rs.z2()).abs();

    assertTrue(
        difference.compareTo(epsilon) < 0,
        "The difference between z1 and z2 exceeds the acceptable epsilon threshold for a valid"
            + " input.");
  }

  @Test
  void calc_v_17_bad_path_for_m_less_than_two_thirds() {
    var m = BigDecimal.valueOf(0.5);
    var params = new InParams(m, BigDecimal.ZERO);

    var rs = v_17.expression(params);
    BigDecimal epsilon = new BigDecimal("0.0000000001");
    BigDecimal difference = rs.z1().subtract(rs.z2()).abs();

    assertTrue(
        difference.compareTo(epsilon) > 0,
        "For m < 2/3 the formula is not equivalent because the sign of 3m - 2 changes.");
  }

  @Test
  void calc_v_17_bad_path_for_zero_input() {
    var params = new InParams(BigDecimal.ZERO, BigDecimal.ZERO);

    assertThrows(ArithmeticException.class, () -> v_17.expression(params));
  }
}
