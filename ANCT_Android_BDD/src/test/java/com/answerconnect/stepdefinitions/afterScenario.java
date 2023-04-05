package com.answerconnect.stepdefinitions;

import java.io.IOException;
import java.util.Base64;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.answerconnect.base.BaseClass;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class afterScenario extends BaseClass {

	@After(order = 1)
	public static void getScreenshot(Scenario scenario) throws IOException {

		if (scenario.isFailed()) {
			try {
				TakesScreenshot ts = (TakesScreenshot) driver;
				String screenshot = ts.getScreenshotAs(OutputType.BASE64);
				screenshot = screenshot.replaceAll("[^A-Za-z0-9+/]", "");
				byte[] ss = Base64.getDecoder().decode(screenshot);
				scenario.attach(ss, "image/jpeg", "Refer");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				TakesScreenshot ts = (TakesScreenshot) driver;
				String screenshot = ts.getScreenshotAs(OutputType.BASE64);
				screenshot = screenshot.replaceAll("[^A-Za-z0-9+/]", "");
				byte[] ss = Base64.getDecoder().decode(screenshot);
				scenario.attach(ss, "image/jpeg", "Refer");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
