package org.example.calculator;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorE2ETest {

    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;

    @BeforeAll
    public static void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));  // Headless=false for debugging
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterAll
    public static void tearDown() {
        browser.close();
        playwright.close();
    }

    @BeforeEach
    public void beforeEachTest() {
        page.navigate("http://localhost:8080");  // Navigate to your app before each test
    }

    @Test
    public void testComputeMean() {
        page.fill("#values", "1, 2, 3, 4, 5");
        page.click("button:has-text('Compute Mean')");

        // Wait for the result to appear and verify if it's correct
        String resultText = page.locator("#result").textContent();
        if (resultText.contains("Error")) {
            assertTrue(resultText.contains("Error calculating mean"));
        } else {
            assertEquals("Mean: 3.00", resultText.trim());
        }
    }

    @Test
    public void testComputeSampleStdDev() {
        page.fill("#values", "1, 2, 3, 4, 5");
        page.click("button:has-text('Compute Sample Standard Deviation')");

        String resultText = page.locator("#result").textContent();
        if (resultText.contains("Error")) {
            assertTrue(resultText.contains("Error calculating sample standard deviation"));
        } else {
            assertEquals("Sample Standard Deviation: 1.58", resultText.trim());
        }
    }

    @Test
    public void testComputePopulationStdDev() {
        page.fill("#values", "1, 2, 3, 4, 5");
        page.click("button:has-text('Compute Population Standard Deviation')");

        String resultText = page.locator("#result").textContent();
        if (resultText.contains("Error")) {
            assertTrue(resultText.contains("Error calculating population standard deviation"));
        } else {
            assertEquals("Population Standard Deviation: 1.41", resultText.trim());
        }
    }

    @Test
    public void testComputeZScore() {
        page.fill("#values", "5, 3, 1");  // Comma-separated values for z-score calculation
        page.click("button:has-text('Compute Z Score')");

        String resultText = page.locator("#result").textContent();
        if (resultText.contains("Error")) {
            assertTrue(resultText.contains("Error calculating Z-score"));
        } else {
            assertEquals("Z-Score: 2.00", resultText.trim());
        }
    }

    @Test
    public void testComputeLinearRegression() {
        page.fill("#values", "1, 2\n2, 3\n3, 4\n4, 5");
        page.click("button:has-text('Compute Linear Regression Formula')");

        String resultText = page.locator("#result").textContent();
        if (resultText.contains("Error")) {
            assertTrue(resultText.contains("Error calculating linear regression"));
        } else {
            assertEquals("y = 1.00x + 1.00", resultText.trim());
        }
    }

    @Test
    public void testComputeYFromLinearRegression() {
        page.fill("#values", "5, 1, 1");  // Comma-separated values for x, m, b
        page.click("button:has-text('Compute Y from Regression Formula')");

        String resultText = page.locator("#result").textContent();
        if (resultText.contains("Error")) {
            assertTrue(resultText.contains("Error calculating Y from regression"));
        } else {
            assertEquals("y = 6.00 (from y = 1.00x + 1.00)", resultText.trim());
        }
    }

    @Test
    public void testInvalidLinearRegression() {
        page.fill("#values", "1, 2\n1, 3\n1, 4");  // Constant x values
        page.click("button:has-text('Compute Linear Regression Formula')");

        String resultText = page.locator("#result").textContent();
        assertTrue(resultText.contains("Cannot calculate linear regression. All x-values are the same."));
    }
}
