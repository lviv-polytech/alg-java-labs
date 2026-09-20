package com.lpnu.lb4.service.task5;

/**
 * Contract for checking if a coordinate (x,y) hits a mathematically defined shape area.
 */
public interface ShapeHitTester {
    
    /**
     * Determines whether the given point falls within the defined area.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     * @param r the radius / scale parameter of the shape
     * @return true if the point is inside or exactly on the boundary of the shape, false otherwise
     */
    boolean isHit(double x, double y, double r);
}