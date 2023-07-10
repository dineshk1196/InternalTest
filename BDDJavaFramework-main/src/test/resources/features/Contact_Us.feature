@contact-us

Feature: WebDriver University - Contact Us Page

  Background:
    Given I access the webdriver university contact us page

    @TCContact @Smoke
  Scenario: Validate Successful Submission - Unique Data
    When I enter a unique first name
    And I enter a unique last name
    And I enter a unique email address
    And I enter a unique comment
    And I click on the submit button
    Then I should be presented with a successful contact us submission message
 @Smoke
  Scenario: Validate Successful Submission - Specific Data
    When I enter a specific first name sarah
    And I enter a specific last name woods
    And I enter a specific email address sarah_woods123@mail.com
    And I enter a specific comment "How are you today?"
    And I click on the submit button
    Then I should be presented with a successful contact us submission message
@regression
  Scenario Outline: Validate Successful Submission - Excel Data
    Given the actor is configured with below variables
      |TestCaseId|
    |<TestCaseId>|
    When I enter a specific first name, last name, email and comment
    And I click on the submit button
    Then I should be presented with a successful contact us submission message
    Examples:
    |TestCaseId|
    |1         |
