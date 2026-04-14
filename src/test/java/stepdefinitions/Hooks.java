package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks extends BaseTest {

    @Before
    public void setUpScenario() {

        // Read browser from system property
        // If nothing is passed, default to chrome
        String browser = System.getProperty("browser", "chrome");

        // Launch browser for Cucumber scenario
        launchBrowserForCucumber(browser, "Cucumber Scenario");

        // Open default login URL
        getDriver().get(config.getLoginUrl());
    }

    @After
    public void tearDownScenario() {

        // Close browser after each scenario
        quitBrowserForCucumber();
    }
}