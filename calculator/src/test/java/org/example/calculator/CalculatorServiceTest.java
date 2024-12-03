package org.example.calculator;

import org.example.calculator.model.CalculationResult;
import org.example.calculator.service.CalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

public class CalculatorServiceTest {

    private CalculatorService calculatorService;

    @BeforeEach
    public void setUp() {
        calculatorService = new CalculatorService();
    }

    // Test Mean Calculation
    @Test
    public void testComputeMean() {
        List<Double> values = List.of(1.0, 2.0, 3.0, 4.0, 5.0);
        CalculationResult result = calculatorService.computeMean(values);

        assertEquals("Mean", result.getOperation());
        assertEquals(3.0, result.getResult(), 0.001);
    }

    // Test Sample Standard Deviation Calculation
    @Test
    public void testComputeSampleStdDev() {
        List<Double> values = List.of(1.0, 2.0, 3.0, 4.0, 5.0);
        CalculationResult result = calculatorService.computeSampleStdDev(values);

        assertEquals("Sample Standard Deviation", result.getOperation());
        assertEquals(1.58, result.getResult(), 0.01);  // Approximate value for std dev
    }

    // Test Population Standard Deviation Calculation
    @Test
    public void testComputePopulationStdDev() {
        List<Double> values = List.of(1.0, 2.0, 3.0, 4.0, 5.0);
        CalculationResult result = calculatorService.computePopulationStdDev(values);

        assertEquals("Population Standard Deviation", result.getOperation());
        assertEquals(1.41, result.getResult(), 0.01);  // Approximate value for std dev
    }

    // Test Linear Regression Calculation
    @Test
    public void testComputeLinearRegression() {
        List<CalculatorService.Point> points = List.of(
                new CalculatorService.Point(1, 2),
                new CalculatorService.Point(2, 3),
                new CalculatorService.Point(3, 4),
                new CalculatorService.Point(4, 5)
        );
        CalculationResult result = calculatorService.computeLinearRegression(points);

        assertEquals("Linear Regression", result.getOperation());
        assertEquals("y = 1.00x + 1.00", result.getResult());
    }

    // Test Computing Y from Linear Regression
    @Test
    public void testComputeYFromLinearRegression() {
        double x = 5.0;
        double slope = 1.0;
        double intercept = 1.0;
        CalculationResult result = calculatorService.computeYFromRegression(x, slope, intercept);

        assertEquals("Y from Regression", result.getOperation());
        assertEquals(6.0, result.getResult(), 0.001); // y = 1.0 * 5.0 + 1.0
    }

    // Test Z-Score Calculation
    @Test
    public void testComputeZScore() {
        double value = 10.0;
        double mean = 5.0;
        double stdDev = 2.5;
        CalculationResult result = calculatorService.computeZScore(value, mean, stdDev);

        assertEquals("Z-Score", result.getOperation());
        assertEquals(2.0, result.getResult(), 0.001); // (10 - 5) / 2.5 = 2.0
    }

    // Test Z-Score calculation when stdDev is zero (edge case)
    @Test
    public void testComputeZScoreWithZeroStdDev() {
        double value = 10.0;
        double mean = 5.0;
        double stdDev = 0.0;
        CalculationResult result = calculatorService.computeZScore(value, mean, stdDev);

        assertEquals("Z-Score", result.getOperation());
        assertEquals(0.0, result.getResult(), 0.001); // Handle division by zero
    }

    // Test Linear Regression when there's not enough data
    @Test
    public void testComputeLinearRegressionWithInsufficientData() {
        List<CalculatorService.Point> points = List.of(new CalculatorService.Point(1, 2));
        CalculationResult result = calculatorService.computeLinearRegression(points);

        assertEquals("Linear Regression", result.getOperation());
        assertEquals("Not enough data", result.getResult());
    }
}
