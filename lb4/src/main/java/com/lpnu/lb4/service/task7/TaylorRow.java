package com.lpnu.lb4.service.task7;

/**
 * Represents a single row in the Taylor series tabulation table.
 */
public record TaylorRow(double x, double mathLog, double taylorLog, int termsCount) {
}