package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class FirstTest extends BaseTest {
	
	 @Test
	    public void openGoogleTest() {
 
 
		 // open Google
		 driver.get("https://www.google.com/");
		 
		 // get the title
		 String title = driver.getTitle();
		 
		 // print the title
		 System.out.println("Page Title is: " + title);
		 
		 // assert that title contains Google
		 Assert.assertTrue(title.contains("Google"));

	        driver.quit();
	    }
	

}
