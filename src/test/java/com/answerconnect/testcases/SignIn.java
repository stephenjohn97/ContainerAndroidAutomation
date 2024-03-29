package com.answerconnect.testcases;

import java.io.IOException;

import com.answerconnect.utilities.CommonUtilities;

public class SignIn extends CommonUtilities {

	static String validEmail = getData("validEmail");
	static String invalidEmail = getData("invalidEmail");
	static String wrongEmail = getData("wrongEmail");
	static String validPassword = getData("validPassword");
	static String invalidPassword = getData("invalidPassword");
	static String wrongPassword = getData("wrongPassword");
	static String callUs = getData("callUs");
	static String termsPrivacy = getData("termsPrivacy");
	static String signInWithGoogle = getData("SIWG");

//Check the login screen UI
	public static void acLogo() {
		if (findAccID("acLogo").isDisplayed()) {
			logPass("AnswerConnect logo");
		} else {
			logFail("AnswerConnect logo");
		}
	}

	public static void forgetPasswordButtonText() {
		if (findAccID("forgetPasswordBtn").isDisplayed()) {
			logPass("Forget Password button");
		} else {
			logFail("Forget Password button");
		}
	}

	public static void signInWithGoogleLogo() {
		if (findAccID("signInwithGLogo").isDisplayed()) {
			logPass("Sign in with Google logo");
		} else {
			logFail("Sign in with Google logo");
		}
	}

	public static void signInWithGoogleText() {
		if (findXpath("signInwithGText").isDisplayed()) {
			assertEqualsString(findXpath("signInwithGText").getText(), signInWithGoogle);
		} else {
			logFail("Sign in with Google text");
		}
	}

	public static void callUSText() {
		if (findAccID("callUs").isDisplayed()) {
			assertEqualsString(findAccID("callUs").getText(), callUs);
		} else {
			logFail("Call Us");
		}
	}

	public static void termsAndPrivacyText() {
		if (findAccID("terms&Privacy").isDisplayed()) {
			assertEqualsString(findAccID("terms&Privacy").getText(), termsPrivacy);
		} else {
			logFail("Terms & Privacy");
		}
	}

// Scenario: Check the Forget Password flow
	public static void forgetPasswordButton() throws InterruptedException {
		tapOn("Forget Password", findAccID("forgetPasswordBtn"));
	}

	public static void enterInvalidEmailIDFP() {
		clearField(findXpath("FPemailField"));
		sendKeys(findXpath("FPemailField"), invalidEmail);
	}

	public static void tapResetPassword() throws InterruptedException {
		tapOn("Reset password", findAccID("resetPassword"));
	}

	public static void validateInvalidToastMessage() {
		assertEquals(findXpath("ToastMessage"), "Please enter a valid email ID");
	}

	public static void enterValidEmailIDFP() throws InterruptedException {
		tempWait(4000);
		clearField(findXpath("FPemailField"));
		sendKeys(findXpath("FPemailField"), validEmail);
	}

	public static void validateResetPassword() {
		assertEquals(findXpath("ToastMessage"), "Reset Password Link has send to your mail");
	}

// ***INVALID CRED SCENARIO***
	public static void enterWrongEmail() {
		clearField(findXpath("emailField"));
		sendKeys(findXpath("emailField"), wrongEmail);
	}

	public static void enterWrongPassword() {
		clearField(findXpath("passwordField"));
		sendKeys(findXpath("passwordField"), wrongPassword);
	}

	public static void validateWrongLoginToast() {
		assertEquals(findXpath("ToastMessage"), "Invalid Login Credentials");
		log("Wrong credential toast validated successfully");
	}

// ***INVALID SCENARIO***
	public static void enterInvalidEmail() throws InterruptedException {
		tempWait(2000);
		clearField(findXpath("emailField"));
		sendKeys(findXpath("emailField"), invalidEmail);
	}

	public static void enterInvalidPassword() {
		clearField(findXpath("passwordField"));
		sendKeys(findXpath("passwordField"), invalidPassword);
	}

	public static void validateInvalidLoginToast() {
		assertEquals(findXpath("ToastMessage"), "Please enter the correct email");
		log("Invalid credential toast validated successfully");
	}

// ***VALID SCENARIO***
	public static void enterEmailId() {
		clearField(findXpath("emailField"));
		sendKeys(findXpath("emailField"), validEmail);
	}

	public static void enterPassword() {
		clearField(findXpath("passwordField"));
		sendKeys(findXpath("passwordField"), validPassword);
	}

	public static void tapLoginButton() throws InterruptedException {
		tapOn("Login button", findAccID("loginButton"));
	}

	public static void skipTourPage() throws InterruptedException {
		if (findAccID("skipTour").isDisplayed()) {
			tapOn("Skip", findAccID("skipTour"));
		} else {
			log("Tour page does not exist");
		}
	}

	public static void validateSignIn() throws IOException {
		assertEquals(findXpath("inboxTab"), "Inbox");
		log("Sign In validated successfully");
	}

}
