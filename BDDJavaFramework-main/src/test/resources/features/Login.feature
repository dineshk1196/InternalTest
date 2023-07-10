@Login
Feature: Bamboo-Login Page - Login Page

  Background:
    Given I access the Bamboo login page

  Scenario: Validate Successful Login
    And user logs into WebDriver University Application
      |User Role|
      |WebDriver|
    Then I should be presented with the successful login message

  @TC123 @Test @Smoke @Regression
  Scenario: Validate Unsuccessful Login
    When I enter a username
    And I click on next button
    And I enter a password
    And I click on the login button
   # And I click on New Quote button
    #Then I should be presented with the unsuccessful login message

  Scenario Outline: Validate - Successful & Unsuccessful Login
    When I enter a username <username>
    And I enter a password <password>
    And I click on the login button
    Then I should be presented with the following login validation message <loginValidationMessage>

    Examples:
      | username  | password     | loginValidationMessage |
      | webdriver | webdriver123 | validation succeeded   |
      | webdriver | webdriver1   | validation failed      |
      | joe_blogs | password1    | validation failed      |

