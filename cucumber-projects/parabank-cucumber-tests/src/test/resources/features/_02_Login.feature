Feature: Login functionality
  Background:
    Given Navigate to the ParaBank

  @smoke
  Scenario: Login with a newly registered account
    When Enter credentials for a new account
    And Click login button
    Then User should successfully login to the system
    And User should logout from the system

  Scenario: Reject an incorrect password
    When Enter an existing username and an incorrect password
    And Click login button
    Then An unsuccessful login message should be displayed

  Scenario: Reject a nonexistent username
    When Enter a nonexistent username
    And Click login button
    Then An unsuccessful login message should be displayed
