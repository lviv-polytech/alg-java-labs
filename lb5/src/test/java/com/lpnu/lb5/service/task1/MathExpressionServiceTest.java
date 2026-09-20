package com.lpnu.lb5.service.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MathExpressionServiceTest {

    private final MathExpressionService service = new MathExpressionService();

    @Test
    void testCalculateH() {
        // x=1, y=2, z=3
        // num = 1+2+3 = 6
        // den = 1+4+9 = 14
        // res = 6/14 = 3/7 ~= 0.428571
        double res = service.calculateH(1.0, 2.0, 3.0);
        Assertions.assertEquals(6.0 / 14.0, res, 1e-6);
    }
    
    @Test
    void testCalculateH_DivisionByZero() {
        // x=0, y=0, z=0
        double res = service.calculateH(0.0, 0.0, 0.0);
        Assertions.assertTrue(Double.isNaN(res));
    }

    @Test
    void testEvaluateCompositeExpression() {
        // Let's test with a=1, b=1
        // h(a,b,1) = h(1,1,1) = 3 / 3 = 1.0
        // h(1,a,b) = h(1,1,1) = 3 / 3 = 1.0
        // Numerator = 1.0 + 1.0 = 2.0
        // h(a^2+b^2, 1, 0) = h(2, 1, 0) = (2+1+0)/(4+1+0) = 3/5 = 0.6
        // Denominator = 1 + 0.6 = 1.6
        // Result = 2.0 / 1.6 = 1.25
        double res = service.evaluateCompositeExpression(1.0, 1.0);
        Assertions.assertEquals(1.25, res, 1e-6);
    }
}