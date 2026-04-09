package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EbayHomePage;

public class EbayNavigationTest extends BaseTest {

    @Test(groups = {"regression"})
    public void searchAndNavigateTest() {

        // Open eBay homepage from config
        openUrl(config.getNavigationUrl());

        // Create page object using current thread's driver
        EbayHomePage home = new EbayHomePage(getDriver());

        // Search for an item
        home.searchForItem("laptop");

        // Validate that search results page title contains the keyword
        Assert.assertTrue(
                home.isSearchResultDisplayed("laptop"),
                "Search results page title does not contain 'laptop'"
        );

        // Navigate back to homepage
        getDriver().navigate().back();

        // Validate that we are back on the home page
        Assert.assertTrue(
                getDriver().getTitle().toLowerCase().contains("ebay"),
                "Did not navigate back to homepage correctly"
        );
    }
}