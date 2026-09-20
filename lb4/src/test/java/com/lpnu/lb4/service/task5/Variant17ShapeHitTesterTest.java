package com.lpnu.lb4.service.task5;

import com.lpnu.lb4.infra.task5.Variant17ShapeHitTester;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Variant17ShapeHitTesterTest {

    private final ShapeHitTester tester = new Variant17ShapeHitTester();

    @Test
    void testArea1_InnerHit() {
        // R = 5, top-left circle center is (-5, 0)
        // Point x = -2.5, y = 2.5
        // (x+5)^2 + y^2 = (2.5)^2 + (2.5)^2 = 6.25 + 6.25 = 12.5 <= 25 (Hit)
        Assertions.assertTrue(tester.isHit(-2.5, 2.5, 5.0));
    }

    @Test
    void testArea1_BoundaryHit() {
        // R = 5. Boundary point directly above center: x = -5, y = 5
        // (-5+5)^2 + 5^2 = 0 + 25 = 25 <= 25 (Hit)
        Assertions.assertTrue(tester.isHit(-5.0, 5.0, 5.0));
    }

    @Test
    void testArea1_Miss() {
        // R = 5, point outside the circle: (-1, 5)
        // (-1+5)^2 + 5^2 = 16 + 25 = 41 > 25 (Miss)
        Assertions.assertFalse(tester.isHit(-1.0, 5.0, 5.0));
    }

    @Test
    void testArea2_InnerHit() {
        // R = 5, bottom-right circle center is (5, 0)
        // Point x = 2.5, y = -2.5
        // (x-5)^2 + y^2 = (-2.5)^2 + (-2.5)^2 = 6.25 + 6.25 = 12.5 <= 25 (Hit)
        Assertions.assertTrue(tester.isHit(2.5, -2.5, 5.0));
    }

    @Test
    void testArea2_BoundaryHit() {
        // R = 5. Boundary point directly below center: x = 5, y = -5
        Assertions.assertTrue(tester.isHit(5.0, -5.0, 5.0));
    }
    
    @Test
    void testArea2_Miss() {
        // R = 5, point outside: (10, -5)
        // (10-5)^2 + (-5)^2 = 25 + 25 = 50 > 25 (Miss)
        Assertions.assertFalse(tester.isHit(10.0, -5.0, 5.0));
    }

    @Test
    void testEmptyQuadrants() {
        // R = 10
        // Quadrant I (x > 0, y > 0) should always miss
        Assertions.assertFalse(tester.isHit(5.0, 5.0, 10.0));
        
        // Quadrant III (x < 0, y < 0) should always miss
        Assertions.assertFalse(tester.isHit(-5.0, -5.0, 10.0));
    }
    
    @Test
    void testNegativeRadiusThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> tester.isHit(1.0, 1.0, -5.0));
    }
}