package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GooglePage {
	
	WebDriver driver;
	
	//Constructor
	public GooglePage(WebDriver driver) {;
	this.driver = driver;
	}
	
	// Locators
	By searchBox = By.id("APjFqb");
	
	//Actions
	public void enterSearch(String text) {
		driver.findElement(searchBox).sendKeys(text);
	}

}
