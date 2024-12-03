package org.example.calculator;

import org.example.calculator.controller.CalculatorController;
import org.example.calculator.model.CalculationResult;
import org.example.calculator.service.CalculatorService;
import org.example.calculator.service.CalculatorService.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(CalculatorController.class)
public class CalculatorApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CalculatorService calculatorService;

    @InjectMocks
    private CalculatorController calculatorController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    // Test case for computing Mean
    @Test
    public void testComputeMean() throws Exception {
        CalculationResult mockResult = new CalculationResult("Mean", 3.0);
        when(calculatorService.computeMean(List.of(1.0, 2.0, 3.0, 4.0, 5.0))).thenReturn(mockResult);

        mockMvc.perform(post("/api/calculator/mean")
                        .contentType("application/json")
                        .content("[1,2,3,4,5]"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"operation\":\"Mean\",\"result\":3.0}"));
    }

    // Test case for computing Sample Standard Deviation
    @Test
    public void testComputeSampleStdDev() throws Exception {
        CalculationResult mockResult = new CalculationResult("Sample Standard Deviation", 1.58);
        when(calculatorService.computeSampleStdDev(List.of(1.0, 2.0, 3.0, 4.0, 5.0))).thenReturn(mockResult);

        mockMvc.perform(post("/api/calculator/sample-std-dev")
                        .contentType("application/json")
                        .content("[1,2,3,4,5]"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"operation\":\"Sample Standard Deviation\",\"result\":1.58}"));
    }

    // Test case for computing Linear Regression
    @Test
    public void testComputeLinearRegression() throws Exception {
        CalculationResult mockResult = new CalculationResult("Linear Regression", "y = 1.00x + 1.00");
        when(calculatorService.computeLinearRegression(List.of(
                new Point(1, 2), new Point(2, 3), new Point(3, 4), new Point(4, 5))))
                .thenReturn(mockResult);

        mockMvc.perform(post("/api/calculator/linear-regression")
                        .contentType("application/json")
                        .content("[[1,2], [2,3], [3,4], [4,5]]"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"operation\":\"Linear Regression\",\"result\":\"y = 1.00x + 1.00\"}"));
    }

    // Test case for computing Y from Linear Regression
    @Test
    public void testComputeYFromLinearRegression() throws Exception {
        CalculationResult mockResult = new CalculationResult("Y from Regression", 6.0);
        when(calculatorService.computeYFromRegression(5.0, 1.0, 1.0)).thenReturn(mockResult);

        mockMvc.perform(post("/api/calculator/compute-y")
                        .contentType("application/json")
                        .content("[5,1,1]"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"operation\":\"Y from Regression\",\"result\":6.0}"));
    }

    // Test case for computing Z-Score
    @Test
    public void testComputeZScore() throws Exception {
        CalculationResult mockResult = new CalculationResult("Z-Score", 2.0);
        when(calculatorService.computeZScore(10.0, 5.0, 2.5)).thenReturn(mockResult);

        mockMvc.perform(post("/api/calculator/z-score")
                        .contentType("application/json")
                        .content("[10,5,2.5]"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"operation\":\"Z-Score\",\"result\":2.0}"));
    }
}
