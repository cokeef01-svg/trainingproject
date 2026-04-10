package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class openAmazonTest extends BaseTest {
	
	@Test
	public void openAmazonTest() {
		
		//open amazon.com
		getDriver().get("https://www.amazon.com/");
		
		// get the title of the page
		Assert.assertTrue(getDriver().getTitle().contains("Amazon"));
		
		getDriver().quit();
	}
	

}
