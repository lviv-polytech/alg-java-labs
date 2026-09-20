package com.lpnu.lb5.service.task5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GeometricProgressionServiceTest {

    private final GeometricProgressionService service = new GeometricProgressionService();
    private final RecursionContext dummyCtx = new RecursionContext(null);

    @Test
    void testCalculateNthTerm() {
        // b1=2, q=3, n=4 => b4 = 2 * 3^3 = 2 * 27 = 54
        double b4 = service.calculateNthTerm(2.0, 3.0, 4, 1, dummyCtx);
        Assertions.assertEquals(54.0, b4, 1e-9);
        Assertions.assertEquals(4, dummyCtx.getMaxDepth());
    }

    @Test
    void testCalculateSum() {
        // b1=2, q=3, n=4 
        // sum = b1 + b2 + b3 + b4 
        // b1=2, b2=6, b3=18, b4=54
        // sum = 2 + 6 + 18 + 54 = 80
        RecursionContext sumCtx = new RecursionContext(null);
        double s4 = service.calculateSum(2.0, 3.0, 4, 1, sumCtx);
        Assertions.assertEquals(80.0, s4, 1e-9);
        Assertions.assertEquals(4, sumCtx.getMaxDepth());
    }
    
    @Test
    void testBaseCase() {
        double b1Term = service.calculateNthTerm(5.0, 2.0, 1, 1, dummyCtx);
        Assertions.assertEquals(5.0, b1Term, 1e-9);
        
        double b1Sum = service.calculateSum(5.0, 2.0, 1, 1, dummyCtx);
        Assertions.assertEquals(5.0, b1Sum, 1e-9);
    }
}