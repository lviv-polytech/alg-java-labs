package com.lpnu.alg_lb3.var.task4;

/**
 * Contract for checking whether a point with given coordinates (x, y) lies within a specific
 * geometric region defined by radius R.
 *
 * <p>The target region consists of two quarter-circle segments:
 *
 * <ul>
 *   <li>Region A (Quadrant II, top-left): centered at (-R, 0) with radius R
 *   <li>Region B (Quadrant IV, bottom-right): centered at (R, 0) with radius R
 * </ul>
 *
 * <p>This interface abstracts the geometric validation logic, allowing the implementation to remain
 * hidden while exposing a clean contract for callers.
 */
public interface PointRegionChecker {

  /**
   * Checks if the point (x, y) lies within the shaded region defined by radius R.
   *
   * <p>The shaded region is composed of:
   *
   * <ul>
   *   <li><b>Region A (Quadrant II):</b> -R ≤ x ≤ 0, 0 ≤ y ≤ R, and (x + R)² + y² ≤ R²
   *   <li><b>Region B (Quadrant IV):</b> 0 ≤ x ≤ R, -R ≤ y ≤ 0, and (x - R)² + y² ≤ R²
   * </ul>
   *
   * @param x the x-coordinate of the point
   * @param y the y-coordinate of the point
   * @param R the radius parameter (must be positive and finite)
   * @return {@code true} if the point is inside the region, {@code false} otherwise
   * @throws IllegalArgumentException if R is non-positive or non-finite
   */
  boolean isInside(double x, double y, double R);

  /**
   * Creates a default implementation of the {@link PointRegionChecker}.
   *
   * @return a new instance of the point region checker
   */
  static PointRegionChecker create() {
    return new PointRegionCheckerImpl();
  }
}
