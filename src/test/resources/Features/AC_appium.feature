#Author: stephen.john@anywhere.co
@AC_Android
Feature: Login Page

  @OpenApp
  Scenario: Open the AnswerConnect application
    Given open the applicaton

  @LoginScreenUI
  Scenario: Check the login screen UI
    Given validate the AnswerConnect logo
    And validate the Forgot Password button text
    And validate the Sign in with Google logo
    Then validate the Sign in with Google text
    And validate the text in Call Us
    Then validate the text in Terms & Privacy

  @ForgetPasswordCheck
  Scenario: Check the Forget Password flow
    Given tap on forget password button
    When enter the invalid email ID in FP
    And tap on Reset password button
    Then validate the invalid toast message
    When enter the valid email ID in FP
    And tap on Reset password button
    Then validate the Reset password link

  @WrongCredentialsCheck
  Scenario: Check the Login functionalites with wrong credentials
    When enter the wrong email ID
    When enter the wrong password
    And tap on login button
    Then validate the wrong login toast message
    
  @InvalidLoginCheck
  Scenario: Check the Login functionalites with invalid data
    When enter the invalid email ID
    When enter the invalid password
    And tap on login button
    Then validate the invalid login toast message

  @ValidLoginCheck
  Scenario: Check the Login functionalities with valid data
    When enter the email ID
    When enter the password
    And tap on login button
    And skip tour page
    Then validate signIn page
