package com.answerconnect.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.answerconnect.utilities.CommonUtilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseClass extends ExcelRead {

	public static AndroidDriver<AndroidElement> driver;
	public static Properties property = new Properties();
	static InputStream input = null;
	static ServerSocket socket;
	static int port;
	public static String userName = getData("userName");
	public static String accessKey = getData("accessKey");

	public static void appLaunch() throws InterruptedException, IOException {
		try {
			server().start();
			System.out.println("App launching");
			loadPropertyFiles();
			DesiredCapabilities capabilities = new DesiredCapabilities();
			if (getData("RealDevice").equalsIgnoreCase("No")) {
				capabilities.setCapability("platformName", getData("platformName"));
				capabilities.setCapability("deviceName", getData("deviceName"));
				capabilities.setCapability("platformVersion", getData("platformVersion"));
				capabilities.setCapability("appPackage", getData("appPackage"));
				capabilities.setCapability("appActivity", getData("appActivity"));
				capabilities.setCapability("automationName", getData("automationName"));
				capabilities.setCapability("noReset", false);
				capabilities.setCapability("autoGrantPermissions", true);
				capabilities.setCapability("autoAcceptAlerts", true);
				capabilities.setCapability("skipUnlock", true);
				driver = new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:" + port + "/wd/hub"), capabilities);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			} else if (getData("CloudDevice").equalsIgnoreCase("Yes")) {
				capabilities.setCapability("device", getData("cloudDeviceName"));
				capabilities.setCapability("os_version", getData("OS"));
				capabilities.setCapability("project", getData("projectName"));
				capabilities.setCapability("build", getData("buildName"));
				capabilities.setCapability("name", getData("testName"));
				capabilities.setCapability("app", getData("appID"));
				capabilities.setCapability("autoGrantPermissions", true);
				capabilities.setCapability("autoAcceptAlerts", true);
				capabilities.setCapability("noReset", false);
				capabilities.setCapability("fullReset", true);
				capabilities.setCapability("newCommandTimeout", 120);
				driver = new AndroidDriver<AndroidElement>(
						new URL("https://" + userName + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub"),
						capabilities);
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("App launched");
		CommonUtilities.log("Application launched sucessfully");
	}

	public static void loadPropertyFiles() throws IOException {
		input = new FileInputStream("./src/main/resources/Locators/signIn.properties");
		property.load(input);
		System.out.println();
	}

	public static String prop(String element) {
		return property.getProperty(element);
	}

	public static AppiumDriverLocalService server() throws IOException {
		socket = new ServerSocket(0);
		port = socket.getLocalPort();
		socket.close();
		HashMap<String, String> envi = new HashMap<>();
		envi.put("ANDROID_HOME", "/users/stefan/Library/Android/sdk");
		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		builder.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"));
		builder.usingDriverExecutable(new File("/usr/local/bin/node"));
		builder.usingPort(port);
		builder.withEnvironment(envi);
		builder.withLogFile(new File("Logs.txt"));
		return AppiumDriverLocalService.buildService(builder);
	}

}
