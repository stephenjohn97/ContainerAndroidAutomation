package com.answerconnect.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(tags = "@AC_Android", glue = { "com.answerconnect.stepdefinitions" }, features = {
		"src/test/resources/Features/AC_appium.feature" }, plugin = {
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }, dryRun = false, monochrome = true)

public class RunnerClass {

	/*
	 * @AfterClass public static void email() {
	 * sendEmailWithExtentReportAsAttachment("johnstephen3780@gmail.com", "Test",
	 * null, null); }
	 * 
	 * 
	 * public static void sendEmailWithExtentReportAsAttachment(String toEmail,
	 * String reportFilePath, String subject, String body, String username, String
	 * password) throws EmailException {
	 * 
	 * // Create a new email message MultiPartEmail email = new MultiPartEmail();
	 * email.setHostName("smtp.gmail.com"); email.setSmtpPort(587);
	 * email.setAuthenticator(new DefaultAuthenticator(username, password));
	 * email.setStartTLSEnabled(true); email.setFrom(username);
	 * email.addTo(toEmail); email.setSubject(subject); email.setMsg(body);
	 * 
	 * // Create the email attachment EmailAttachment attachment = new
	 * EmailAttachment(); attachment.setPath(reportFilePath);
	 * attachment.setDisposition(EmailAttachment.ATTACHMENT);
	 * attachment.setName("Extent Report.html");
	 * 
	 * // Add the email attachment to the email message email.attach(attachment);
	 * 
	 * // Send the email email.send(); }
	 */

}
