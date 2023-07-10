@Basic_Details
Feature: Bamboo - Basic Details
  Background:
    Given I access the Bamboo login page
  @TC223 @Test @Smoke @Regression
  Scenario: Validate successful Login
    When I enter a username
    And I click on next button
    And I enter a password
    And I click on the login button
    And I click on New Quote button
    And I changed the tab
    And I Enter the Frist name
    And I Enter the Last name
    And I enter the Email Address
    And I entered the address
    And I clicked on Radio button 1
    And I clicked on Radio button 2
    And I clicked on Radio button 3
    And I clicked on Radio button 4
    Then I clicked on Proceed button
    #Then I should be presented with the unsuccessful login message

