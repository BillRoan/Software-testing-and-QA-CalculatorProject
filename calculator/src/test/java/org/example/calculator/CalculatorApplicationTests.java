import org.example.calculator.CalculatorApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = CalculatorApplication.class)  // Explicitly specify the main class
@AutoConfigureMockMvc
public class CalculatorApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testMeanCalculation() throws Exception {
        // Perform a POST request to your API and check the response
        mockMvc.perform(post("/api/calculator/mean")
                        .contentType("application/json")
                        .content("[1.0, 2.0, 3.0, 4.0, 5.0]"))  // Pass values as JSON
                .andExpect(status().isOk())  // Check if the status is 200 OK
                .andExpect(jsonPath("$.result").value(3.0));  // Verify the result field
    }
}
