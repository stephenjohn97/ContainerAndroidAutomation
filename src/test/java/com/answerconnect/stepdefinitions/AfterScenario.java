package com.answerconnect.stepdefinitions;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.answerconnect.utilities.CommonUtilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.service.ExtentService;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Scenario;

public class AfterScenario extends CommonUtilities {

	@After
	public static void getScreenshot(Scenario scenario) throws IOException {

		if (scenario.isFailed()) {
			try {

				TakesScreenshot ts = (TakesScreenshot) driver;
				String screenshot = ts.getScreenshotAs(OutputType.BASE64);
				screenshot = screenshot.replaceAll("[^A-Za-z0-9+/]", "");
				byte[] ss = Base64.getDecoder().decode(screenshot);
				scenario.attach(ss, "image/png", "Refer");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				TakesScreenshot ts = (TakesScreenshot) driver;
				String screenshot = ts.getScreenshotAs(OutputType.BASE64);
				screenshot = screenshot.replaceAll("[^A-Za-z0-9+/]", "");
				byte[] ss = Base64.getDecoder().decode(screenshot);
				scenario.attach(ss, "image/png", "Refer");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@AfterAll
	public static void openReport() throws IOException, InterruptedException {
		ExtentReports extent = ExtentService.getInstance();
		if (extent != null) {
			extent.flush();
		}
		if (server() != null) {
			server().stop();
		}

		Desktop.getDesktop().browse(new File("Extent-Report/Report/AC_Android.html").toURI());
		driver.quit();
	}

}
