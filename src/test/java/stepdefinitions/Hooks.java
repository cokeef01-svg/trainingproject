package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks extends BaseTest {

    @Before
    public void setUpScenario() {
        launchBrowserForCucumber("chrome", "Cucumber Scenario");
        getDriver().get(config.getLoginUrl());
    }

    @After
    public void tearDownScenario() {
        quitBrowserForCucumber();
    }
}