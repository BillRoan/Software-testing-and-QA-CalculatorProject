package org.example.calculator.model;

public class CalculationResult {

    private String operation;
    private double result;
    private String resultString;

    // Constructor for numerical result
    public CalculationResult(String operation, double result) {
        this.operation = operation;
        this.result = result;
        this.resultString = null;
    }

    // Constructor for string result (e.g., linear regression equation)
    public CalculationResult(String operation, String resultString) {
        this.operation = operation;
        this.result = 0.0;
        this.resultString = resultString;
    }

    public String getOperation() {
        return operation;
    }

    public double getResult() {
        return result;
    }

    public String getResultString() {
        return resultString;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public void setResultString(String resultString) {
        this.resultString = resultString;
    }
}
