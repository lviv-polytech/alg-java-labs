package com.lpnu.alg_lb3.var.task2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class PiecewiseEdgeCasesTest {

  private final PiecewiseFunction fn = new PiecewiseFunction();

  @Test
  void firstCase_computes_quadratic_expression() {
    var a = BigDecimal.valueOf(2);
    var b = BigDecimal.valueOf(1);
    var c = BigDecimal.valueOf(3);
    var x = BigDecimal.valueOf(-20); // x + 10 < 0

    var res = fn.evaluate(a, b, c, x);

    assertEquals(a.multiply(x.pow(2)).subtract(c.multiply(x)).add(b), res);
  }

  @Test
  void secondCase_computes_quadratic_without_division() {
    // For Variant 17, case 2 is F = x^2 - cx (no division)
    var a = BigDecimal.valueOf(1);
    var b = BigDecimal.ZERO;
    var c = BigDecimal.valueOf(5);
    var x = BigDecimal.valueOf(5); // x + 10 > 0 and b == 0

    var res = fn.evaluate(a, b, c, x);
    assertEquals(BigDecimal.ZERO, res); // 5^2 - 5*5 = 0
  }

  @Test
  void secondCase_computes_quadratic_without_division() {
    // For Variant 17, case 2 is F = x^2 - cx (no division)
    var a = BigDecimal.valueOf(5);
    var b = BigDecimal.valueOf(2);
    var c = BigDecimal.valueOf(5);
    var x = BigDecimal.valueOf(5); // x + 10 > 0 and b == 0

    var res = fn.evaluate(a, b, c, x);
    assertEquals(BigDecimal.ZERO, res); // 5^2 - 5*5 = 0
  }
}
