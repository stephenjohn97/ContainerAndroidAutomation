#Author: stephen.john@anywhere.co
@AC_Android
Feature: Login Page

  @OpenApp
  Scenario: Open the AnswerConnect application
    Given open the applicaton

  @InvalidLoginCheck
  Scenario: Check the Login functionalites with invalid data
    When enter the invalid email ID
    When enter the invalid password
    And tap on login button
    Then validate invalid login toast message

  @ValidLoginCheck
  Scenario: Check the Login functionalities with valid data
    When enter the email ID
    When enter the password
    And tap on login button
    # And tap on skip button
    Then validate signIn page
