Feature: Login functionality
  
  @smoke
  Scenario: Valid login
    Given the user is on the login page
    When the user enters username "tomsmith" and password "SuperSecretPassword!"
    And clicks the login button
    Then the user should see a successful login message
    
  @regression
  Scenario: Invalid login
    Given the user is on the login page
    When the user enters username "wronguser" and password "wrongpass"
    And clicks the login button
    Then the user should see a failed login message