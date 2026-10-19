package com.lpnu.alg_lb3.var.task4;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link PointRegionChecker}.
 *
 * <p>Tests cover:
 *
 * <ul>
 *   <li>Points clearly inside each region (Quadrant II and IV)
 *   <li>Points clearly outside the regions
 *   <li>Boundary points on the edges of the regions
 *   <li>Edge cases: origin, axes intersections, exact circle boundaries
 *   <li>Invalid inputs: negative/zero radius, NaN, infinity
 * </ul>
 */
class PointRegionCheckerTest {

  private PointRegionChecker checker;

  @BeforeEach
  void setUp() {
    checker = PointRegionChecker.create();
  }

  // ============================================================
  // Happy Path Tests - Points Inside Quadrant II (top-left)
  // ============================================================

  @Test
  void pointInsideQuadrantII_center() {
    assertTrue(checker.isInside(-1.0, 0.5, 2.0), "Point at (-1, 0.5) should be inside");
  }

  @Test
  void pointInsideQuadrantII_nearBoundary() {
    assertTrue(checker.isInside(-0.5, 1.0, 2.0), "Point at (-0.5, 1) should be inside");
  }

  @Test
  void pointInsideQuadrantII_atOrigin() {
    assertTrue(checker.isInside(0.0, 0.0, 2.0), "Origin should be inside (on boundary)");
  }

  // ============================================================
  // Happy Path Tests - Points Inside Quadrant IV (bottom-right)
  // ============================================================

  @Test
  void pointInsideQuadrantIV_center() {
    assertTrue(checker.isInside(1.0, -0.5, 2.0), "Point at (1, -0.5) should be inside");
  }

  @Test
  void pointInsideQuadrantIV_nearBoundary() {
    assertTrue(checker.isInside(0.5, -1.0, 2.0), "Point at (0.5, -1) should be inside");
  }

  // ============================================================
  // Boundary Tests - Points Exactly on Circle Edges
  // ============================================================

  @Test
  void pointOnCircleEdge_quadrantII_leftmost() {
    // At (-R, 0), which is on the circle centered at (-R, 0)
    assertTrue(checker.isInside(-2.0, 0.0, 2.0), "Point at (-R, 0) should be inside");
  }

  @Test
  void pointOnCircleEdge_quadrantII_topmost() {
    // At (-R, R), which is on the boundary
    assertTrue(checker.isInside(-2.0, 2.0, 2.0), "Point at (-R, R) should be on boundary");
  }

  @Test
  void pointOnCircleEdge_quadrantIV_rightmost() {
    // At (R, 0), which is on the circle centered at (R, 0)
    assertTrue(checker.isInside(2.0, 0.0, 2.0), "Point at (R, 0) should be inside");
  }

  @Test
  void pointOnCircleEdge_quadrantIV_bottommost() {
    // At (R, -R), which is on the boundary
    assertTrue(checker.isInside(2.0, -2.0, 2.0), "Point at (R, -R) should be on boundary");
  }

  @Test
  void pointOnCircleEdge_quadrantII_45degrees() {
    // Point on the arc at 45 degrees from center (-R, 0)
    double R = 2.0;
    double x = -R + R / Math.sqrt(2.0);
    double y = R / Math.sqrt(2.0);
    assertTrue(checker.isInside(x, y, R), "Point on 45° arc in Quadrant II should be on boundary");
  }

  @Test
  void pointOnCircleEdge_quadrantIV_45degrees() {
    // Point on the arc at -45 degrees from center (R, 0)
    double R = 2.0;
    double x = R - R / Math.sqrt(2.0);
    double y = -R / Math.sqrt(2.0);
    assertTrue(checker.isInside(x, y, R), "Point on -45° arc in Quadrant IV should be on boundary");
  }

  // ============================================================
  // Edge Cases - Points Outside the Regions
  // ============================================================

  @Test
  void pointOutside_quadrantI() {
    assertFalse(checker.isInside(1.0, 1.0, 2.0), "Point in Quadrant I should be outside");
  }

  @Test
  void pointOutside_quadrantIII() {
    assertFalse(checker.isInside(-1.0, -1.0, 2.0), "Point in Quadrant III should be outside");
  }

  @Test
  void pointOutside_farLeft() {
    assertFalse(checker.isInside(-10.0, 0.5, 2.0), "Point far to the left should be outside");
  }

  @Test
  void pointOutside_farRight() {
    assertFalse(checker.isInside(10.0, -0.5, 2.0), "Point far to the right should be outside");
  }

  @Test
  void pointOutside_aboveQuadrantII() {
    assertFalse(checker.isInside(-1.0, 5.0, 2.0), "Point above region should be outside");
  }

  @Test
  void pointOutside_belowQuadrantIV() {
    assertFalse(checker.isInside(1.0, -5.0, 2.0), "Point below region should be outside");
  }

  @Test
  void pointOutside_justBeyondCircle_quadrantII() {
    // Point slightly outside the circle in Quadrant II
    double R = 2.0;
    double x = -R + 0.1;
    double y = R + 0.1;
    assertFalse(
        checker.isInside(x, y, R), "Point just outside Quadrant II circle should be outside");
  }

  @Test
  void pointOutside_justBeyondCircle_quadrantIV() {
    // Point slightly outside the circle in Quadrant IV
    double R = 2.0;
    double x = R - 0.1;
    double y = -R - 0.1;
    assertFalse(
        checker.isInside(x, y, R), "Point just outside Quadrant IV circle should be outside");
  }

  // ============================================================
  // Invalid Input Tests
  // ============================================================

  @Test
  void invalidRadius_zero() {
    assertThrows(
        IllegalArgumentException.class,
        () -> checker.isInside(0.0, 0.0, 0.0),
        "Zero radius should throw exception");
  }

  @Test
  void invalidRadius_negative() {
    assertThrows(
        IllegalArgumentException.class,
        () -> checker.isInside(0.0, 0.0, -1.0),
        "Negative radius should throw exception");
  }

  @Test
  void invalidRadius_NaN() {
    assertThrows(
        IllegalArgumentException.class,
        () -> checker.isInside(0.0, 0.0, Double.NaN),
        "NaN radius should throw exception");
  }

  @Test
  void invalidRadius_infinity() {
    assertThrows(
        IllegalArgumentException.class,
        () -> checker.isInside(0.0, 0.0, Double.POSITIVE_INFINITY),
        "Infinite radius should throw exception");
  }

  @Test
  void invalidPoint_NaN_x() {
    assertFalse(checker.isInside(Double.NaN, 0.0, 2.0), "Point with NaN x should be outside");
  }

  @Test
  void invalidPoint_NaN_y() {
    assertFalse(checker.isInside(0.0, Double.NaN, 2.0), "Point with NaN y should be outside");
  }

  @Test
  void invalidPoint_infinity_x() {
    assertFalse(
        checker.isInside(Double.POSITIVE_INFINITY, 0.0, 2.0),
        "Point with infinite x should be outside");
  }

  @Test
  void invalidPoint_infinity_y() {
    assertFalse(
        checker.isInside(0.0, Double.NEGATIVE_INFINITY, 2.0),
        "Point with infinite y should be outside");
  }

  // ============================================================
  // Additional Edge Cases with Different Radii
  // ============================================================

  @Test
  void smallRadius_pointInside() {
    assertTrue(checker.isInside(-0.05, 0.05, 0.1), "Point should be inside with small R");
  }

  @Test
  void largeRadius_pointInside() {
    assertTrue(checker.isInside(-50.0, 50.0, 100.0), "Point should be inside with large R");
  }

  @Test
  void smallRadius_pointOutside() {
    assertFalse(checker.isInside(-1.0, 0.5, 0.1), "Point should be outside with small R");
  }
}
