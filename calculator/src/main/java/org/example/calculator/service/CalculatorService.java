package org.example.calculator.service;

import org.example.calculator.model.CalculationResult;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CalculatorService {

    public static class Point {
        private double x;
        private double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }
    }

    // Compute Mean
    public CalculationResult computeMean(List<Double> values) {
        if (values == null || values.isEmpty()) {
            return new CalculationResult("Mean", "Input list is empty.");
        }
        double mean = values.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        return new CalculationResult("Mean", mean);
    }

    // Compute Sample Standard Deviation
    public CalculationResult computeSampleStdDev(List<Double> values) {
        if (values == null || values.isEmpty()) {
            return new CalculationResult("Sample Standard Deviation", 0.0);
        }
        double mean = computeMean(values).getResult();
        double variance = values.stream().mapToDouble(val -> Math.pow(val - mean, 2)).average().orElse(0.0);
        double sampleStdDev = Math.sqrt(variance);
        return new CalculationResult("Sample Standard Deviation", sampleStdDev);
    }

    // Compute Population Standard Deviation
    public CalculationResult computePopulationStdDev(List<Double> values) {
        if (values == null || values.isEmpty()) {
            return new CalculationResult("Population Standard Deviation", "Input list is empty.");
        }

        try {
            // Calculate the mean of the values
            double mean = computeMean(values).getResult();

            // Calculate the sum of squared differences from the mean
            double sumOfSquaredDifferences = values.stream()
                    .mapToDouble(val -> Math.pow(val - mean, 2))  // (xi - mean)^2
                    .sum();

            // Avoid division by zero if there's only one value
            double variance = sumOfSquaredDifferences / values.size();

            // Take the square root of the variance to get the population standard deviation
            double populationStdDev = Math.sqrt(variance);

            return new CalculationResult("Population Standard Deviation", populationStdDev);
        } catch (Exception e) {
            return new CalculationResult("Population Standard Deviation", "Error during calculation: " + e.getMessage());
        }
    }

    // Compute Linear Regression
    public CalculationResult computeLinearRegression(List<Point> points) {
        if (points == null || points.size() < 2) {
            return new CalculationResult("Linear Regression", "Not enough data points. Minimum 2 points are required.");
        }

        double n = points.size();
        double sumX = points.stream().mapToDouble(Point::getX).sum();
        double sumY = points.stream().mapToDouble(Point::getY).sum();
        double sumXY = points.stream().mapToDouble(p -> p.getX() * p.getY()).sum();
        double sumX2 = points.stream().mapToDouble(p -> Math.pow(p.getX(), 2)).sum();


        // Check for the denominator to avoid division by zero
        double denominator = (n * sumX2 - Math.pow(sumX, 2));
        if (denominator == 0) {
            return new CalculationResult("Linear Regression", "Error: Denominator is zero, which means data points may be collinear.");
        }

        // Calculate slope (m)
        double slope = (n * sumXY - sumX * sumY) / denominator;

        // Calculate intercept (b)
        double intercept = (sumY - slope * sumX) / n;
        

        // Return result as a string in the form "y = mx + b"
        String regressionEquation = "y = " + String.format("%.2fx + %.2f", slope, intercept);

        return new CalculationResult("Linear Regression", regressionEquation);
    }

    // Compute Y from Linear Regression Formula
    public CalculationResult computeYFromRegression(double x, double slope, double intercept) {
        double y = slope * x + intercept;
        return new CalculationResult("Y from Regression", y);
    }

    // Compute Z-Score
    public CalculationResult computeZScore(double value, double mean, double stdDev) {
        if (stdDev == 0) {
            return new CalculationResult("Z-Score", 0.0);  // Handle division by zero
        }
        double zScore = (value - mean) / stdDev;
        return new CalculationResult("Z-Score", zScore);
    }
}
