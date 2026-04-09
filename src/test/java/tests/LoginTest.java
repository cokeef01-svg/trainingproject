package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ExcelUtils;

public class LoginTest extends BaseTest {

    /**
     * DataProvider
     * ------------
     * Reads login test data from Excel for regression testing.
     *
     * Each row in the Excel sheet becomes one test run.
     */
    @DataProvider(name = "loginData")
    public Object[][] getData() {

        // Path to Excel file
        String filePath = "src/test/resources/testdata/LoginData.xlsx";

        // Name of the Excel sheet
        String sheetName = "Sheet1";

        // Read and return Excel data
        return ExcelUtils.getExcelData(filePath, sheetName);
    }

    /**
     * validLoginSmokeTest
     * -------------------
     * A quick smoke test using one known-good login only.
     *
     * Smoke tests should be:
     * - fast
     * - simple
     * - stable
     *
     * So we do not use Excel here.
     */
    @Test(groups = {"smoke"})
    public void validLoginSmokeTest() {

        // Debug line to confirm URL from config
        System.out.println("Login URL: " + config.getLoginUrl());

        // Open login page
        openUrl(config.getLoginUrl());

        // Create page object
        LoginPage login = new LoginPage(getDriver());

        // Use one valid known-good login and validate that it is successful
        login.login("tomsmith", "SuperSecretPassword!")
        .verifyLoginSuccessful();
        
    }

    /**
     * loginTest
     * ---------
     * Full regression test using Excel data.
     *
     * This runs once per row in LoginData.xlsx.
     */
    @Test(dataProvider = "loginData", groups = {"regression"})
    public void loginTest(String username, String password, String expected) {

        // Debug line to confirm URL from config
        System.out.println("Login URL: " + config.getLoginUrl());

        // Open login page
        openUrl(config.getLoginUrl());

        // Create page object
        LoginPage login = new LoginPage(getDriver());

        // Perform login using Excel data AND Validate actual result against expected result from Excel
        login.login(username, password);

        if (expected.equalsIgnoreCase("success")) {
            login.verifyLoginSuccessful();
        } else {
            login.verifyLoginFailed();
        }
    }
}