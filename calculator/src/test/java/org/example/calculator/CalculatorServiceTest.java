import org.example.calculator.model.CalculationResult;
import org.example.calculator.service.CalculatorService;
import org.example.calculator.service.CalculatorService.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class CalculatorServiceTest {

    private CalculatorService calculatorService;

    @BeforeEach
    public void setUp() {
        calculatorService = new CalculatorService();
    }

    @Test
    public void testComputeMean() {
        List<Double> values = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        CalculationResult result = calculatorService.computeMean(values);
        assertEquals(3.0, result.getResult(), "Mean should be 3.0");
    }

    @Test
    public void testComputeSampleStdDev() {
        List<Double> values = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        CalculationResult result = calculatorService.computeSampleStdDev(values);
        // Correct sample standard deviation value is approximately 1.414 (rounded to 1.41)
        assertEquals(1.41, result.getResult(), 0.01, "Sample Standard Deviation should be around 1.41");
    }

    @Test
    public void testComputePopulationStdDev() {
        List<Double> values = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        CalculationResult result = calculatorService.computePopulationStdDev(values);
        assertEquals(1.41, result.getResult(), 0.01, "Population Std Dev should be around 1.41");
    }

    @Test
    public void testComputeLinearRegression() {
        List<Point> points = Arrays.asList(
                new Point(1, 2),
                new Point(2, 3),
                new Point(3, 4)
        );
        CalculationResult result = calculatorService.computeLinearRegression(points);
        assertTrue(result.getResultString().contains("y = 1.00x + 1.00"));
    }

    @Test
    public void testComputeYFromRegression() {
        double x = 2.0;
        double slope = 1.0;
        double intercept = 1.0;
        CalculationResult result = calculatorService.computeYFromRegression(x, slope, intercept);
        assertEquals(3.0, result.getResult(), "y should be 3.0 based on y = x + 1");
    }

    @Test
    public void testComputeZScore() {
        double value = 2.0;
        double mean = 3.0;
        double stdDev = 1.0;
        CalculationResult result = calculatorService.computeZScore(value, mean, stdDev);
        assertEquals(-1.0, result.getResult(), "Z-score should be -1.0");
    }
}
