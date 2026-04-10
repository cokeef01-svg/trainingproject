package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.GooglePage;

public class GoogleSearchTest extends BaseTest {
	
	@Test
	public void GoogleSearchTest() throws InterruptedException {

   
	   // goto google.com
		getDriver().get("https://www.google.com");
	   
	   // handle cookie popup if it appears
	   try {
		   getDriver().findElement(By.xpath("//button[.='Accept all']")).click();
	   } catch (Exception e) {
		   System.out.println("No cookie popup displayed");
	   }
	   
	   // find the search bar element and send keys 'Selenium Webdriver' (below is old code)
	   //driver.findElement(By.id("APjFqb")).sendKeys("Selenium WebDriver");
	   // New code using GooglePage to enter text in search box
	   GooglePage google = new GooglePage(getDriver());
	   google.enterSearch("Selenium WebDriver");
		
	   // sleep for 2 seconds
	    Thread.sleep(2000);

	    getDriver().quit();
	}
	
}
