package org.example.calculator;

import org.example.calculator.model.CalculationResult;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculationResultTests {

    @Test
    public void testCalculationResultConstructor() {
        CalculationResult result = new CalculationResult("Mean", 3.0);
        assertEquals("Mean", result.getOperation());
        assertEquals(3.0, result.getResult());
    }

    @Test
    public void testSetters() {
        CalculationResult result = new CalculationResult("Mean", 3.0);
        result.setOperation("Standard Deviation");
        result.setResult(2.5);

        assertEquals("Standard Deviation", result.getOperation());
        assertEquals(2.5, result.getResult());
    }
}
