package stepdefinitions;

import org.testng.Assert;

import base.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;

public class LoginSteps extends BaseTest {

    // Page object for login page
    private LoginPage loginPage;

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {

        // Hooks already opened browser and navigated to URL
        // Just initialise the page object here
        loginPage = new LoginPage(getDriver());
    }

    @When("the user enters username {string} and password {string}")
    public void the_user_enters_username_and_password(String username, String password) {

        // Enter credentials using page object methods
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("clicks the login button")
    public void clicks_the_login_button() {

        // Click login button
        loginPage.clickLogin();
    }

    @Then("the user should see a successful login message")
    public void the_user_should_see_a_successful_login_message() {

        // Verify successful login message is displayed
        Assert.assertTrue(
            loginPage.verifyLoginSuccessful(),
            "Login was not successful"
        );
    }

    @Then("the user should see a failed login message")
    public void the_user_should_see_a_failed_login_message() {

        // Verify failure message is displayed for invalid login
        Assert.assertTrue(
            loginPage.verifyLoginFailed(),
            "Login failure message was not shown"
        );
    }
}