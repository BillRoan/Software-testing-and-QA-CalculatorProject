import org.example.calculator.model.CalculationResult;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculationResultTests {

    @Test
    public void testCalculationResultWithNumericResult() {
        CalculationResult result = new CalculationResult("Mean", 5.0);
        assertEquals("Mean", result.getOperation());
        assertEquals(5.0, result.getResult());
        assertNull(result.getResultString());
    }

    @Test
    public void testCalculationResultWithStringResult() {
        CalculationResult result = new CalculationResult("Linear Regression", "y = 1.00x + 1.00");
        assertEquals("Linear Regression", result.getOperation());
        assertEquals("y = 1.00x + 1.00", result.getResultString());
        assertEquals(0.0, result.getResult());  // Default for string-based result
    }

    @Test
    public void testSettersAndGetters() {
        CalculationResult result = new CalculationResult("Test Operation", 10.0);
        result.setOperation("Updated Operation");
        result.setResult(15.0);
        result.setResultString("Updated Result");

        assertEquals("Updated Operation", result.getOperation());
        assertEquals(15.0, result.getResult());
        assertEquals("Updated Result", result.getResultString());
    }
}
