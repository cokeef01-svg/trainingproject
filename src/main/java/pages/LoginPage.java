package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private By username = By.id("username");
    private By password = By.id("password");
    private By loginBtn = By.cssSelector("button[type='submit']");
    private By message = By.id("flash");

    public void enterUsername(String user) {
        type(username, user);
    }

    public void enterPassword(String pass) {
        type(password, pass);
    }

    public void clickLogin() {
        click(loginBtn);
    }

    public void login(String user, String pass) {
        enterUsername(user);
        enterPassword(pass);
        clickLogin();
    }

    public String getMessage() {
        return getText(message);
    }

    public boolean verifyLoginSuccessful() {
        return getMessage().contains("You logged into a secure area");
    }

    public boolean verifyLoginFailed() {
        String flashMessage = getMessage();
        return flashMessage.contains("Your username is invalid!")
                || flashMessage.contains("Your password is invalid!");
    }
}