package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {

    public static String takeScreenshot(WebDriver driver, String testName) {

        // Add milliseconds to reduce filename collisions
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));

        // Add thread id as extra protection during parallel runs
        long threadId = Thread.currentThread().getId();

        String filePath = "screenshots/" + testName + "_" + timestamp + "_T" + threadId + ".png";

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            Files.createDirectories(new File("screenshots").toPath());

            // REPLACE_EXISTING avoids copy errors if a duplicate somehow still occurs
            Files.copy(src.toPath(), new File(filePath).toPath(), StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return filePath;
    }
}