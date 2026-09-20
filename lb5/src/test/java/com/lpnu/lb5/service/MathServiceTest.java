package com.lpnu.lb5.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MathServiceTest {

    @Test
    void testSum() {
        // Arrange
        MathService mathService = new MathService();
        int expectedResult = 15;

        // Act
        int actualResult = mathService.sum(10, 5);

        // Assert
        Assertions.assertEquals(expectedResult, actualResult, "The sum of 10 and 5 should be 15.");
    }
}