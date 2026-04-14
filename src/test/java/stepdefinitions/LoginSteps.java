package stepdefinitions;

import org.testng.Assert;

import base.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;

public class LoginSteps extends BaseTest {

    private LoginPage loginPage;

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        launchBrowserForCucumber("chrome", "Valid login scenario");
        getDriver().get(config.getLoginUrl());
        loginPage = new LoginPage(getDriver());
    }

    @When("the user enters username {string} and password {string}")
    public void the_user_enters_username_and_password(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("clicks the login button")
    public void clicks_the_login_button() {
        loginPage.clickLogin();
    }

    @Then("the user should see a successful login message")
    public void the_user_should_see_a_successful_login_message() {
        Assert.assertTrue(loginPage.verifyLoginSuccessful(), "Login was not successful");
        quitBrowserForCucumber();
    }

    @Then("the user should see a failed login message")
    public void the_user_should_see_a_failed_login_message() {
        Assert.assertTrue(loginPage.verifyLoginFailed(), "Login failure message was not shown");
        quitBrowserForCucumber();
    }
}