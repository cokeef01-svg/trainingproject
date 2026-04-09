package utils;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

            // Create reports folder if it does not exist
            String reportsDir = System.getProperty("user.dir") + "/reports";
            new File(reportsDir).mkdirs();

            // Full report file path
            String reportPath = reportsDir + "/ExtentReport_" + timestamp + ".html";

            System.out.println("Extent report path: " + reportPath);

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setDocumentTitle("Training Project Report");
            spark.config().setReportName("Automation Test Results");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Project", "trainingProject");
            extent.setSystemInfo("Framework", "Selenium + TestNG");
        }

        return extent;
    }
}