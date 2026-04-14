package stepdefinitions;

import org.testng.Assert;

import base.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.EbayHomePage;

public class EbaySteps extends BaseTest {

    // Page object for eBay
    private EbayHomePage ebayHomePage;

    @Given("the user is on the eBay home page")
    public void the_user_is_on_the_eBay_home_page() {

        // Open eBay URL from config
        getDriver().get(config.getNavigationUrl());

        // Create eBay page object
        ebayHomePage = new EbayHomePage(getDriver());
    }

    @When("the user searches for {string}")
    public void the_user_searches_for(String searchText) {

        // Enter search text and click search
    	ebayHomePage.searchForItem(searchText);
    }

    @Then("eBay search results should contain {string}")
    public void eBay_search_results_should_contain(String expectedText) {

        // Check that the results page contains the expected text
    	Assert.assertTrue(
    			ebayHomePage.isSearchResultDisplayed(expectedText),
    	        "Search results do not contain: " + expectedText
    		);
    }
}