import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Paths;

public class CalculatorE2ETest {

    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;

    @BeforeAll
    public static void setup() {
        // Launch Playwright and the browser
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)); // false for debugging
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterAll
    public static void tearDown() {
        // Clean up after the tests
        page.close();
        context.close();
        browser.close();
        playwright.close();
    }

    @Test
    public void testMeanCalculation() {
        // Navigate to the calculator web page
        page.navigate("http://localhost:8080"); // Adjust this URL if needed

        // Input values
        page.locator("#values").fill("1\n2\n3\n4\n5");

        // Click the "Compute Mean" button
        page.locator("button:has-text('Compute Mean')").click();

        // Wait for the result and check the output
        String resultText = page.locator("#result").innerText();
        assertTrue(resultText.contains("Mean: 3.00"), "Mean calculation failed!");
    }

    @Test
    public void testSampleStdDevCalculation() {
        // Navigate to the calculator web page
        page.navigate("http://localhost:8080"); // Ensure this is the correct URL

        // Input values
        page.locator("#values").fill("1\n2\n3\n4\n5");

        // Click the "Compute Sample Standard Deviation" button
        page.locator("button:has-text('Compute Sample Standard Deviation')").click();

        // Wait for the result and check the output
        String resultText = page.locator("#result").innerText();
        assertTrue(resultText.contains("Sample Standard Deviation"), "Sample Std Dev calculation failed!");
    }

    @Test
    public void testPopulationStdDevCalculation() {
        // Navigate to the calculator web page
        page.navigate("http://localhost:8080");

        // Input values
        page.locator("#values").fill("1\n2\n3\n4\n5");

        // Click the "Compute Population Standard Deviation" button
        page.locator("button:has-text('Compute Population Standard Deviation')").click();

        // Wait for the result and check the output
        String resultText = page.locator("#result").innerText();
        assertTrue(resultText.contains("Population Standard Deviation"), "Population Std Dev calculation failed!");
    }

    @Test
    public void testLinearRegression() {
        // Navigate to the calculator web page
        page.navigate("http://localhost:8080");

        // Input points for linear regression
        page.locator("#values").fill("1,2\n2,3\n3,4");

        // Click the "Compute single Linear Regression Formula" button
        page.locator("button:has-text('Compute single Linear Regression Formula')").click();

        // Wait for the result and check the output
        String resultText = page.locator("#result").innerText();
        assertTrue(resultText.contains("y = 1.00x + 1.00"), "Linear regression calculation failed!");
    }

    @Test
    public void testYFromLinearRegression() {
        // Navigate to the calculator web page
        page.navigate("http://localhost:8080"); // Ensure this is the correct URL

        // Input values for Y = mx + b (e.g., x = 2, slope = 1, intercept = 1)
        page.locator("#values").fill("2,1,1");

        // Click the "Predict Y from Regression Formula" button
        page.locator("button:has-text('Predict Y from Regression Formula')").click();

        // Wait for the result and check the output
        String resultText = page.locator("#result").innerText();
        assertTrue(resultText.contains("y = 3.00"), "Y from Linear Regression calculation failed!");
    }

    @Test
    public void testZScoreCalculation() {
        // Navigate to the calculator web page
        page.navigate("http://localhost:8080");

        // Input values for Z-Score calculation
        page.locator("#values").fill("2,3,1");  // value = 2, mean = 3, stdDev = 1

        // Click the "Compute Z Score" button
        page.locator("button:has-text('Compute Z Score')").click();

        // Wait for the result and check the output
        String resultText = page.locator("#result").innerText();
        assertTrue(resultText.contains("Z-Score: -1.00"), "Z-Score calculation failed!");
    }

    @Test
    public void testInvalidInputForMean() {
        // Navigate to the calculator web page
        page.navigate("http://localhost:8080"); // Ensure this is the correct URL

        // Input empty values for mean
        page.locator("#values").fill("");

        // Click the "Compute Mean" button
        page.locator("button:has-text('Compute Mean')").click();

        // Wait for the result and check the error message
        String resultText = page.locator("#result").innerText();
        assertTrue(resultText.contains("Invalid input, values list cannot be empty"), "Error message for empty input should be shown");
    }
}
