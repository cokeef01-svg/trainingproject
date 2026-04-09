package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    // private WebDriver driver;  // ThreadLocal driver will be passed from test

    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By username = By.id("username");
    private By password = By.id("password");
    private By loginBtn = By.cssSelector("button[type='submit']");
    private By message = By.id("flash");

    // Actions
    public void enterUsername(String user) {
        type(username, user);
    }

    public void enterPassword(String pass) {
        type(password, pass);
    }

    public void clickLogin() {
        click(loginBtn);
    }

    // Combined action - login
    public void login(String user, String pass) {
        enterUsername(user);
        enterPassword(pass);
        clickLogin();
    }

    // Get message text with wait
    public String getMessage() {
        return getText(message);
    }
    
    // is login succeesful
    public boolean isLoginSuccessful() {
        return getText(message).contains("You logged into a secure area");
    }
}