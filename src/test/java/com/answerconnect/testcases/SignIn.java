package com.answerconnect.testcases;

import java.io.IOException;

import com.answerconnect.base.ExcelRead;

public class SignIn extends ExcelRead {

// ***INVALID SCENARIO***
	public static void enterInvalidEmail() {
		clearField(findXpath("emailField"));
		sendKeys(findXpath("emailFied"), invalidEmail);
		log("Entered: " + invalidEmail);
	}

	public static void enterInvalidPassword() {
		clearField(findXpath("passwordField"));
		sendKeys(findXpath("passwordField"), password);
		log("Entered: " + password);
	}

	public static void validateLoginToast() {
		assertEquals(findXpath("ToastMessage"), "Invalid Login Credentials");
		log("Invalid cred toast appeared successfully");
	}

// ***VALID SCENARIO***
	public static void enterEmailId() {
		clearField(findXpath("emailField"));
		sendKeys(findXpath("emailField"), validEmail);
		log("Entered: " + validEmail);
	}

	public static void enterPassword() {
		clearField(findXpath("passwordField"));
		sendKeys(findXpath("passwordField"), password);
		log("Entered: " + password);
	}

	public static void tapLoginButton() throws InterruptedException {
		tapOn("Login button", findAccID("loginButton"));
	}

	/*
	 * public static void tapSkipButton() throws InterruptedException {
	 * wait(findID("skipTourPage")); tapOn("Skip", findID("skipTourPage")); }
	 */

	public static void validateSignIn() throws IOException {
		assertEquals(findXpath("inboxTab"), "Inbox");
		log("Sign In validated successfully");
	}

}
