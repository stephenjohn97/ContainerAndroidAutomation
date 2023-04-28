package com.answerconnect.stepdefinitions;

import java.io.IOException;

import com.answerconnect.base.BaseClass;
import com.answerconnect.testcases.SignIn;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SignInPage {

	// Scenario: Open the AnswerConnect application
	@Given("open the applicaton")
	public void openApp() throws InterruptedException, IOException {
		BaseClass.appLaunch();
	}

	//******** Scenario: Check the login screen UI*********
	@Given("validate the AnswerConnect logo")
	public void acLogo() {
		SignIn.acLogo();
	}

	@And("validate the Forgot Password button text")
	public void forgetPasswordButtonText() {
		SignIn.forgetPasswordButtonText();
	}

	@And("validate the Sign in with Google logo")
	public void signInWithGoogleLogo() {
		SignIn.signInWithGoogleLogo();
	}

	@Then("validate the Sign in with Google text")
	public void signInWithGoogleText() {
		SignIn.signInWithGoogleText();
	}

	@And("validate the text in Call Us")
	public void callUSText() {
		SignIn.callUSText();
	}

	@Then("validate the text in Terms & Privacy")
	public void termsAndPrivacyText() {
		SignIn.termsAndPrivacyText();
	}

	// *********** Scenario: Check the Forget Password flow************
	@Given("tap on forget password button")
	public void forgetPasswordButton() throws InterruptedException {
		SignIn.forgetPasswordButton();
	}

	@When("enter the invalid email ID in FP")
	public void enterInvalidEmailIDFP() {
		SignIn.enterInvalidEmailIDFP();
	}

	@And("tap on Reset password button")
	public void tapResetPassword() throws InterruptedException {
		SignIn.tapResetPassword();
	}

	@Then("validate the invalid toast message")
	public void validateInvalidToastMessage() {
		SignIn.validateInvalidToastMessage();
	}

	@When("enter the valid email ID in FP")
	public void enterValidEmailIDFP() throws InterruptedException {
		SignIn.enterValidEmailIDFP();
	}

	@Then("validate the Reset password link")
	public void validateResetPassword() {
		SignIn.validateResetPassword();
	}

// ***INVALID SCENARIO***
	@When("enter the invalid email ID")
	public void enterInvalidEmail() {
		SignIn.enterInvalidEmail();
	}

	@When("enter the invalid password")
	public void enterInvalidPassword() {
		SignIn.enterInvalidPassword();
	}

	@Then("validate invalid login toast message")
	public void validateLoginToast() {
		SignIn.validateLoginToast();
	}

// ***VALID SCENARIO***
	@When("enter the email ID")
	public void enterEmail() {
		SignIn.enterEmailId();
	}

	@When("enter the password")
	public void enterPassword() {
		SignIn.enterPassword();
	}

	@And("tap on login button")
	public void tapLogin() throws InterruptedException {
		SignIn.tapLoginButton();
	}

	@Then("validate signIn page")
	public void validateSignIn() throws IOException {
		SignIn.validateSignIn();
	}

}
