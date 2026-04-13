package base;

// Used to get the current test method name
import java.lang.reflect.Method;

// Selenium imports
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

// TestNG imports
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

// ExtentReports imports
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

// Utility classes
import utils.ConfigReader;
import utils.ExtentManager;
import utils.ScreenshotUtil;

public class BaseTest {

    /**
     * ThreadLocal WebDriver
     * ---------------------
     * Each test thread gets its own WebDriver instance.
     * This is important for parallel execution so tests do not interfere
     * with each other.
     */
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * ConfigReader
     * ------------
     * Used to read values from config.properties
     * such as login URL, navigation URL, etc.
     */
    protected ConfigReader config;

    /**
     * ExtentReports
     * -------------
     * One shared report object for the entire test run.
     */
    protected static ExtentReports extent = ExtentManager.getInstance();

    /**
     * ThreadLocal ExtentTest
     * ----------------------
     * Each running test gets its own report entry.
     * This keeps reporting safe during parallel execution.
     */
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    /**
     * getDriver()
     * -----------
     * Returns the WebDriver for the current thread.
     * Tests and page objects use this to get the correct driver.
     */
    public WebDriver getDriver() {
        return driver.get();
    }

    /**
     * setUp()
     * -------
     * Runs before every test method.
     *
     * What it does:
     * 1. Loads config
     * 2. Creates the correct browser driver
     * 3. Maximizes the browser window
     * 4. Stores driver in ThreadLocal
     * 5. Creates a test entry in ExtentReports
     *
     * browser comes from testng.xml
     * If no browser is passed, it defaults to chrome
     *
     * method is the current test method being run
     * We use it so the report shows the real test name
     */
    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser, Method method) {

        // Load config.properties values
        config = new ConfigReader();

        // Local driver variable used to create browser instance
        WebDriver localDriver;

        // Launch browser based on parameter from testng.xml
        switch (browser.toLowerCase()) {
            case "chrome":
                localDriver = new ChromeDriver();
                break;

            case "edge":
                System.setProperty(
                        "webdriver.edge.driver",
                        "C:\\Conor\\eclipse\\EdgeDriver\\edgedriver_win64\\msedgedriver.exe"
                );

                /**
                 * EdgeOptions for Jenkins / CI
                 * ----------------------------
                 * These options help prevent browser crashes when running
                 * inside Jenkins (especially when running as a Windows service).
                 */
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--headless=new");
                edgeOptions.addArguments("--disable-gpu");
                edgeOptions.addArguments("--window-size=1920,1080");
                edgeOptions.addArguments("--no-sandbox");
                edgeOptions.addArguments("--disable-dev-shm-usage");

                localDriver = new EdgeDriver(edgeOptions);
                break;

            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        // Maximize browser window
        localDriver.manage().window().maximize();

        // Store driver in ThreadLocal
        driver.set(localDriver);

        /**
         * Create a report entry for this test
         * Example:
         * loginTest [chrome]
         * searchAndNavigateTest [edge]
         */
        ExtentTest extentTest = extent.createTest(method.getName() + " [" + browser + "]");

        // Store report entry in ThreadLocal for this thread
        test.set(extentTest);
    }

    /**
     * openUrl()
     * ---------
     * Helper method used by tests to open a URL.
     * Also logs the URL in the Extent report.
     */
    protected void openUrl(String url) {
        getDriver().get(url);

        // Log navigation step in report (only if test exists)
        if (test.get() != null) {
            test.get().log(Status.INFO, "Opened URL: " + url);
        }
    }

    /**
     * tearDown()
     * ----------
     * Runs after every test method.
     *
     * What it does:
     * 1. Marks test as PASS / FAIL / SKIP in report
     * 2. Takes screenshot on failure
     * 3. Closes browser
     * 4. Removes ThreadLocal objects
     * 5. Writes report updates to file
     *
     * IMPORTANT:
     * Added null checks to avoid failures when browser setup fails
     * (e.g. Edge crash in Jenkins)
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        // Get current ExtentTest safely
        ExtentTest currentTest = test.get();

        // Only log if test object exists
        if (currentTest != null) {

            // Test passed
            if (result.getStatus() == ITestResult.SUCCESS) {
                currentTest.log(Status.PASS, "Test passed");
            }

            // Test failed
            else if (result.getStatus() == ITestResult.FAILURE) {
                currentTest.log(Status.FAIL, result.getThrowable());

                // Only take screenshot if driver exists
                if (driver.get() != null) {
                    String screenshotPath = ScreenshotUtil.takeScreenshot(
                            getDriver(),
                            result.getName()
                    );

                    System.out.println("Screenshot saved at: " + screenshotPath);

                    try {
                        // Attach screenshot to Extent report
                        currentTest.addScreenCaptureFromPath(screenshotPath);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            // Test skipped
            else if (result.getStatus() == ITestResult.SKIP) {
                currentTest.log(Status.SKIP, "Test skipped");
            }
        }

        // Quit browser and clean up ThreadLocal driver
        if (driver.get() != null) {
            getDriver().quit();
            driver.remove();
        }

        // Clean up ThreadLocal ExtentTest
        test.remove();

        // Write latest report updates to the HTML file
        extent.flush();
    }
}