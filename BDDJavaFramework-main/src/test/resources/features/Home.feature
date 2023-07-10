@Home
Feature: Bamboo - Home
  Background:
    Given I access the Bamboo login page
  @TC323 @Test @Smoke @Regression
  Scenario: Validate successful Login
    When I enter a username sireesham
    And I click on next button
    And I enter a password Passw0rd#
    And I click on the login button
    And I click on New Quote button
    And I changed the tab
    And I Enter the Frist name SCOTT
    And I Enter the Last name SAUNDERS
    And I enter the Email Address test23@prospect.com
    And I entered the address 10733 E Emerald Ave, Mesa, AZ, 85208
    And I clicked on Radio button 1
    And I clicked on Radio button 2
    And I clicked on Radio button 3
    And I clicked on Radio button 4
    Then I clicked on Proceed button
    And I clicked on create quote button
    And I clicked on proceed button1
    #Then I should be presented with the unsuccessful login message

