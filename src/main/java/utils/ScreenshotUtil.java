package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ScreenshotUtil
 * --------------
 * Utility class to capture screenshots during test execution.
 *
 * Updated to use OutputType.BYTES + Java NIO (modern approach).
 */
public class ScreenshotUtil {

    public static String takeScreenshot(WebDriver driver, String testName) {

        // Add milliseconds to reduce filename collisions
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));

        // Add thread id for parallel execution safety
        long threadId = Thread.currentThread().getId();

        String fileName = testName + "_" + timestamp + "_T" + threadId + ".png";

        // Define screenshots directory
        String screenshotDir = System.getProperty("user.dir") + "/screenshots/";
        Path directoryPath = Paths.get(screenshotDir);
        Path filePath = Paths.get(screenshotDir + fileName);

        try {
            // Create directory if it does not exist
            Files.createDirectories(directoryPath);

            // Take screenshot as bytes (modern approach)
            byte[] screenshotBytes =
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            // Write file using NIO
            Files.write(filePath, screenshotBytes);

        } catch (IOException e) {
            System.out.println("Screenshot capture failed: " + e.getMessage());
        }

        return filePath.toString();
    }
}