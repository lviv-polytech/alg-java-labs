package com.lpnu.lb4.infra.task5;

import com.lpnu.lb4.service.task5.ShapeHitTester;

/**
 * Implementation for Variant 17 geometry:
 * Area 1: x <= 0, y >= 0, (x + R)^2 + y^2 <= R^2   (Top-Left Circle)
 * Area 2: x >= 0, y <= 0, (x - R)^2 + y^2 <= R^2   (Bottom-Right Circle)
 */
public class Variant17ShapeHitTester implements ShapeHitTester {

    private static final double EPSILON = 1e-9;

    @Override
    public boolean isHit(double x, double y, double r) {
        if (r < 0) {
            throw new IllegalArgumentException("Radius R cannot be negative");
        }

        // Soft check to account for floating point inaccuracies near boundaries
        boolean inArea1 = (x <= EPSILON) && (y >= -EPSILON) 
                          && (Math.pow(x + r, 2) + Math.pow(y, 2) <= r * r + EPSILON);

        boolean inArea2 = (x >= -EPSILON) && (y <= EPSILON) 
                          && (Math.pow(x - r, 2) + Math.pow(y, 2) <= r * r + EPSILON);

        return inArea1 || inArea2;
    }
}