package com.answerconnect.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class BaseClass {

	public static AndroidDriver<AndroidElement> driver;
	public static Properties property = new Properties();
	static InputStream input = null;

	public static void launchApp() throws InterruptedException, IOException {
		try {
			System.out.println("launch app started");
			loadPropertyFiles();
			DesiredCapabilities capabilities = new DesiredCapabilities();
			if (prop("deviceType").equalsIgnoreCase("RealDevice")) {
				System.out.println("In real device");
				capabilities.setCapability("platformName", prop("platformName"));
				capabilities.setCapability("deviceName", prop("deviceName"));
				capabilities.setCapability("platformVersion", prop("platformVersion"));
				capabilities.setCapability("appPackage", prop("appPackage"));
				capabilities.setCapability("appActivity", prop("appActivity"));
				capabilities.setCapability("automationName", prop("automationName"));
				capabilities.setCapability("noReset", false);
				capabilities.setCapability("autoGrantPermissions", true);
				capabilities.setCapability("autoAcceptAlerts", true);
				capabilities.setCapability("skipUnlock", true);
				driver = new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("completed launch app");
	}

	public static void loadPropertyFiles() throws IOException {
		input = new FileInputStream("./src/main/resources/mobileProperties/capabilities.properties");
		property.load(input);
		input = new FileInputStream("./src/main/resources/Locators/signIn.properties");
		property.load(input);

	}

	public static String prop(String element) {
		return property.getProperty(element);
	}

}
