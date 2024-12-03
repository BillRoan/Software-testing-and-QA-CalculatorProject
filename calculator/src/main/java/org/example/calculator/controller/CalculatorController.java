package org.example.calculator.controller;

import org.example.calculator.model.CalculationResult;
import org.example.calculator.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    // Compute Mean from a list of values
    @PostMapping("/mean")
    public CalculationResult computeMean(@RequestBody List<Double> values) {
        if (values == null || values.isEmpty()) {
            return new CalculationResult("Mean", "Invalid input, values list cannot be empty");
        }
        return calculatorService.computeMean(values);
    }

    // Compute Sample Standard Deviation from a list of values
    @PostMapping("/sample-std-dev")
    public CalculationResult computeSampleStdDev(@RequestBody List<Double> values) {
        if (values == null || values.isEmpty()) {
            return new CalculationResult("Sample Standard Deviation", "Invalid input, values list cannot be empty");
        }
        return calculatorService.computeSampleStdDev(values);
    }

    // Compute Population Standard Deviation from a list of values
    @PostMapping("/pop-std-dev")
    public CalculationResult computePopulationStdDev(@RequestBody List<Double> values) {
        if (values == null || values.isEmpty()) {
            return new CalculationResult("Population Standard Deviation", "Invalid input, values list cannot be empty");
        }
        return calculatorService.computePopulationStdDev(values);
    }

    // Compute Linear Regression from a list of Points (x, y)
    @PostMapping("/linear-regression")
    public CalculationResult computeLinearRegression(@RequestBody List<CalculatorService.Point> points) {
        if (points == null || points.size() < 2) {
            return new CalculationResult("Linear Regression", "Not enough data points. Minimum 2 points are required.");
        }
        return calculatorService.computeLinearRegression(points);
    }

    // Compute Y from Linear Regression Formula (y = mx + b)
    @PostMapping("/compute-y")
    public CalculationResult computeYFromLinearRegression(@RequestBody List<Double> values) {
        // The request body should contain [x, slope, intercept]
        if (values == null || values.size() != 3) {
            return new CalculationResult("Y from Regression", "Invalid input. Expected 3 values: [x, slope, intercept]");
        }
        double x = values.get(0);
        double slope = values.get(1);
        double intercept = values.get(2);
        return calculatorService.computeYFromRegression(x, slope, intercept);
    }

    // Compute Z-Score from value, mean, and standard deviation
    @PostMapping("/z-score")
    public CalculationResult computeZScore(@RequestBody List<Double> values) {
        // The request body should contain [value, mean, stdDev]
        if (values == null || values.size() != 3) {
            return new CalculationResult("Z-Score", "Invalid number of arguments. Expected: [value, mean, stdDev]");
        }
        double value = values.get(0);
        double mean = values.get(1);
        double stdDev = values.get(2);
        return calculatorService.computeZScore(value, mean, stdDev);
    }
}
