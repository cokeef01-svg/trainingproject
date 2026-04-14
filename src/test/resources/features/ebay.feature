Feature: eBay search functionality

  @regression @ebay
  Scenario: User can search for a laptop on eBay
    Given the user is on the eBay home page
    When the user searches for "laptop"
    Then eBay search results should contain "laptop"