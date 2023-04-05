package com.answerconnect.stepdefinitions;

import java.io.IOException;

import com.answerconnect.base.BaseClass;
import com.answerconnect.testcases.SignIn;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SignInPage {

	@Given("open the applicaton")
	public void openApp() throws InterruptedException, IOException {
		BaseClass.launchApp();
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

	/*
	 * @And("tap on skip button") public void tapSkip() throws InterruptedException
	 * { SignIn.tapSkipButton(); }
	 */

	@Then("validate signIn page")
	public void validateSignIn() throws IOException {
		SignIn.validateSignIn();
	}

}
