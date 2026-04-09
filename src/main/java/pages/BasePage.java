package pages;

// Import Selenium classes
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Imports for waits (VERY important for stability)
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

// For setting wait time
import java.time.Duration;

// 🔥 NEW: Import Extent logging
import com.aventstack.extentreports.Status;
import base.BaseTest;

/**
 * BasePage
 * --------
 * This is a parent class that all Page Objects will extend.
 * It contains reusable methods like click, type, getText.
 */
public class BasePage {

    // WebDriver instance (comes from your Test via constructor)
    protected WebDriver driver;

    // WebDriverWait instance (used for explicit waits)
    protected WebDriverWait wait;

    /**
     * Constructor
     * -----------
     * Every page that extends BasePage will call this constructor.
     * It sets:
     *  - the driver
     *  - the wait object
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;

        // Create a wait with 5 seconds timeout
        // This means Selenium will wait up to 5 seconds for conditions
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    /**
     * click()
     * -------
     * Clicks an element ONLY when it is clickable.
     * Prevents errors like:
     *  - Element not clickable
     *  - Element not ready
     */
    protected void click(By locator) {

        // Wait until the element is clickable, then click it
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();

        // 🔥 NEW: Log the click action in Extent report
        BaseTest.test.get().log(Status.INFO, "Clicked element: " + locator);
    }

    /**
     * type()
     * ------
     * Sends text to an input field safely.
     * Steps:
     * 1. Wait for element to be visible
     * 2. Clear existing text
     * 3. Enter new text
     */
    protected void type(By locator, String text) {

        // Wait until element is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        // Clear existing text (important for inputs)
        driver.findElement(locator).clear();

        // Type new text
        driver.findElement(locator).sendKeys(text);

        // 🔥 NEW: Log the typing action
        BaseTest.test.get().log(Status.INFO,
                "Entered text '" + text + "' into element: " + locator);
    }

    /**
     * getText()
     * ---------
     * Gets text from an element AFTER it becomes visible.
     * Useful for:
     *  - messages
     *  - labels
     *  - validation
     */
    protected String getText(By locator) {

        // Wait until element is visible, then return its text
        String value = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        ).getText();

        // 🔥 NEW: Log the retrieved text
        BaseTest.test.get().log(Status.INFO,
                "Retrieved text from element: " + locator + " -> " + value);

        return value;
    }
}