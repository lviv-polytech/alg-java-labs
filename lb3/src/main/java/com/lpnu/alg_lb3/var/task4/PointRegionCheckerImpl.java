package com.lpnu.alg_lb3.var.task4;

/**
 * Package-private implementation of {@link PointRegionChecker}.
 *
 * <p>This class evaluates whether a point (x, y) lies within a region composed of two
 * quarter-circle segments:
 *
 * <ul>
 *   <li><b>Region A (Quadrant II):</b> A quarter-circle in the top-left quadrant, centered at (-R,
 *       0) with radius R. The region is bounded by -R ≤ x ≤ 0, 0 ≤ y ≤ R, and satisfies (x + R)² +
 *       y² ≤ R².
 *   <li><b>Region B (Quadrant IV):</b> A quarter-circle in the bottom-right quadrant, centered at
 *       (R, 0) with radius R. The region is bounded by 0 ≤ x ≤ R, -R ≤ y ≤ 0, and satisfies (x -
 *       R)² + y² ≤ R².
 * </ul>
 *
 * <p>A small tolerance ({@code TOLERANCE = 1e-9}) is applied to account for floating-point
 * precision errors during boundary checks.
 *
 * <p>The implementation follows the contract pattern, ensuring that the internal logic remains
 * hidden while exposing a clean interface for clients.
 */
class PointRegionCheckerImpl implements PointRegionChecker {

  /** Tolerance for floating-point comparisons to handle precision errors. */
  private static final double TOLERANCE = 1e-9;

  /**
   * Evaluates whether the given point (x, y) is within the shaded region defined by radius R.
   *
   * <p>The target region is defined as:
   *
   * <ul>
   *   <li><b>Region A (Quadrant II, top-left):</b>
   *       <ul>
   *         <li>-R ≤ x ≤ 0
   *         <li>0 ≤ y ≤ R
   *         <li>(x + R)² + y² ≤ R²
   *       </ul>
   *   <li><b>Region B (Quadrant IV, bottom-right):</b>
   *       <ul>
   *         <li>0 ≤ x ≤ R
   *         <li>-R ≤ y ≤ 0
   *         <li>(x - R)² + y² ≤ R²
   *       </ul>
   * </ul>
   *
   * @param x the x-coordinate of the point
   * @param y the y-coordinate of the point
   * @param R the radius parameter (must be positive and finite)
   * @return {@code true} if the point is inside the region, {@code false} otherwise
   * @throws IllegalArgumentException if R is non-positive or non-finite
   */
  @Override
  public boolean isInside(double x, double y, double R) {
    validateRadius(R);

    if (!isFinite(x) || !isFinite(y)) {
      return false;
    }

    double rSquared = R * R;

    boolean inUpperLeft = checkQuadrantII(x, y, R, rSquared);
    boolean inLowerRight = checkQuadrantIV(x, y, R, rSquared);

    return inUpperLeft || inLowerRight;
  }

  /**
   * Checks if the point lies in Quadrant II (top-left region).
   *
   * <p>Conditions:
   *
   * <ul>
   *   <li>-R ≤ x ≤ 0
   *   <li>0 ≤ y ≤ R
   *   <li>(x + R)² + y² ≤ R²
   * </ul>
   *
   * @param x the x-coordinate
   * @param y the y-coordinate
   * @param R the radius
   * @param rSquared R squared (R²)
   * @return {@code true} if the point is in the upper-left region
   */
  private boolean checkQuadrantII(double x, double y, double R, double rSquared) {
    return x >= -R - TOLERANCE
        && x <= TOLERANCE
        && y >= -TOLERANCE
        && y <= R + TOLERANCE
        && (x + R) * (x + R) + y * y <= rSquared + TOLERANCE;
  }

  /**
   * Checks if the point lies in Quadrant IV (bottom-right region).
   *
   * <p>Conditions:
   *
   * <ul>
   *   <li>0 ≤ x ≤ R
   *   <li>-R ≤ y ≤ 0
   *   <li>(x - R)² + y² ≤ R²
   * </ul>
   *
   * @param x the x-coordinate
   * @param y the y-coordinate
   * @param R the radius
   * @param rSquared R squared (R²)
   * @return {@code true} if the point is in the lower-right region
   */
  private boolean checkQuadrantIV(double x, double y, double R, double rSquared) {
    return x >= -TOLERANCE
        && x <= R + TOLERANCE
        && y <= TOLERANCE
        && y >= -R - TOLERANCE
        && (x - R) * (x - R) + y * y <= rSquared + TOLERANCE;
  }

  /**
   * Validates that the radius R is positive and finite.
   *
   * @param R the radius to validate
   * @throws IllegalArgumentException if R is non-positive or non-finite
   */
  private void validateRadius(double R) {
    if (!isFinite(R) || R <= 0.0) {
      throw new IllegalArgumentException("Radius R must be a finite positive number.");
    }
  }

  /**
   * Checks if a value is finite (not NaN or infinite).
   *
   * @param value the value to check
   * @return {@code true} if the value is finite
   */
  private boolean isFinite(double value) {
    return Double.isFinite(value);
  }
}
