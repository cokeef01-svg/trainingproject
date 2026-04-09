package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EbayHomePage extends BasePage {
	
	private WebDriver driver;
	
	public EbayHomePage(WebDriver driver) {
		super(driver);
	}
	
	// Locators
	private By searchbox = By.id("gh-ac");
	private By searchBtn = By.cssSelector(".gh-search-button__label");
	
	// Actions
	// clear the searchbox and enter text
	public void enterSearchItem(String item) {
		type(searchbox, item);
	}
	
	// click the search box button
	public void clickSearch() {
		click(searchBtn);
	}
	
	// combined action
	public void searchForItem(String item) {
		enterSearchItem(item);
		clickSearch();
	}
	
	// search result displayed
	public boolean isSearchResultDisplayed(String item) {
	    return driver.getTitle().toLowerCase().contains(item.toLowerCase());
	}
	

}
