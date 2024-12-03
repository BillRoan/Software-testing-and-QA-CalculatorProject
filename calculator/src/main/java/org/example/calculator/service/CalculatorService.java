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
            return new CalculationResult("Mean", 0.0);
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
            return new CalculationResult("Population Standard Deviation", 0.0);
        }

        // Calculate the mean of the values
        double mean = computeMean(values).getResult();

        // Calculate the sum of squared differences from the mean
        double sumOfSquaredDifferences = values.stream()
                .mapToDouble(val -> Math.pow(val - mean, 2))  // (xi - mean)^2
                .sum();

        // Divide by the number of elements to get the variance
        double variance = sumOfSquaredDifferences / values.size();

        // Take the square root of the variance to get the standard deviation
        double populationStdDev = Math.sqrt(variance);

        return new CalculationResult("Population Standard Deviation", populationStdDev);
    }

    // Compute Linear Regression
    public CalculationResult computeLinearRegression(List<Point> points) {
        if (points == null || points.size() < 2) {
            return new CalculationResult("Linear Regression", "Not enough data");
        }

        double n = points.size();
        double sumX = points.stream().mapToDouble(Point::getX).sum();
        double sumY = points.stream().mapToDouble(Point::getY).sum();
        double sumXY = points.stream().mapToDouble(p -> p.getX() * p.getY()).sum();
        double sumX2 = points.stream().mapToDouble(p -> Math.pow(p.getX(), 2)).sum();

        double slope = (n * sumXY - sumX * sumY) / (n * sumX2 - Math.pow(sumX, 2));
        double intercept = (sumY - slope * sumX) / n;

        return new CalculationResult("Linear Regression", "y = " + String.format("%.2fx + %.2f", slope, intercept));
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
