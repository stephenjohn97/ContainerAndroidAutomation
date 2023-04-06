package com.answerconnect.utilities;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.answerconnect.base.BaseClass;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.github.javafaker.Faker;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class CommonUtilities extends BaseClass {

	static WebDriverWait wait = new WebDriverWait(driver, 60);
	static Faker faker;

	public static boolean tapOn(String name, WebElement element) throws InterruptedException {
		try {
			try {
				element.click();
			} catch (Exception e) {
				wait(element);
				element.click();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean wait(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void hideKeyboard() {
		driver.hideKeyboard();
	}

	public static void goBack() {
		driver.navigate().back();
	}

	public static void sendKeys(WebElement element, String input) {
		try {
			element.sendKeys(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	public static void assertEqualsString(String actual, String expected) {
		Assert.assertEquals(actual, expected);
	}

	public static String getBase64() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	}

	public static void tempWait(long millis) throws InterruptedException {
		Thread.sleep(millis);
	}

	public static void clearField(WebElement element) {
		element.clear();
	}

	public static void assertEquals(WebElement element, String input) {
		String getheadertext = element.getText().trim();
		System.out.println(getheadertext);
		Assert.assertEquals(input, getheadertext);
	}

	public static void softAssertEquals(WebElement element, String input) {
		String getheadertext = element.getText();
		System.out.println(getheadertext);
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(input, getheadertext);
		sa.assertAll();
	}

	public static WebElement findID(String element) {
		return driver.findElement(By.id(prop(element)));
	}

	public static WebElement findXpath(String element) {
		return driver.findElement(By.xpath(prop(element)));
	}

	public static WebElement findAccID(String element) {
		return driver.findElementByAccessibilityId(prop(element));
	}

	public static boolean assertEqualsWebElement(WebElement actual, WebElement expected, String name) {
		boolean pass = false;
		try {
			Assert.assertEquals(actual.getText(), expected.getText(), name);
			pass = true;
		} catch (Exception e) {
			pass = false;
			System.out.println("AssetEquals exception::::" + e);
			getBase64();
		}
		return pass;
	}

	public static void timePicker(String hours) {
		String hour = driver.findElements(By.id("android:id/numberpicker_input")).get(0).getText();
		int hourOne = Integer.parseInt(hour);
		String hourGiven = hours;
		int hourTwo = Integer.parseInt(hourGiven);
		try {
			while (true) {

				if (hourOne == hourTwo) {
					break;
				} else if (hourOne < hourTwo) {
					driver.findElementByAndroidUIAutomator(
							"new UiScrollable(new UiSelector().resourceIdMatches(\"android:id/numberpicker_input\")).flingForward()");
				} else if (hourOne > hourTwo) {
					driver.findElementByAndroidUIAutomator(
							"new UiScrollable(new UiSelector().resourceIdMatches(\"android:id/numberpicker_input\")).flingForward()");
				}
				hourOne++;
			}
		} catch (Exception e) {
			System.out.println("Can't do");
		}

	}

	public static void doubleTap(String name, WebElement element) {
		try {
			((JavascriptExecutor) driver).executeScript("mobile: doubleClickGesture",
					ImmutableMap.of("elementId", ((RemoteWebElement) element).getId()));
		} catch (Exception e) {
			e.printStackTrace();
			getBase64();
		}
	}

	public static void scrollInto(String name) {
		try {
			driver.findElement(MobileBy.AndroidUIAutomator(
					"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
							+ name + "\").instance(0))"))
					.click();
		} catch (Exception e) {
			System.out.println("ScrollInto exception::::" + e);
		}
	}

	public static void scrollUntil(String name) {
		try {
			driver.findElement(MobileBy.AndroidUIAutomator(
					"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
							+ name + "\").instance(0))"));
		} catch (Exception e) {
			System.out.println("ScrollUntil exception::::" + e);
		}
	}

	public static void assertNotNull(WebElement locator, String name) {
		String element = locator.getText();
		System.out.println(element);
		Assert.assertNotNull(element);
	}

	public static String validate(AndroidDriver<AndroidElement> driver) {
		String result = null;
		File scrFile = driver.getScreenshotAs(OutputType.FILE);
		ITesseract tess = new Tesseract();
		tess.setDatapath(new File("src/test/resources/toasts").getPath());
		try {
			result = tess.doOCR(scrFile);
		} catch (TesseractException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return result;
	}

	public static void swipeLeft() {
		try {
			Dimension dimension = driver.manage().window().getSize();
			int startX = (int) (dimension.width * 0.8);
			int startY = (int) (dimension.height * 0.5);
			int endX = (int) (dimension.width * 0.2);
			int endY = (int) (dimension.height * 0.5);
			AndroidTouchAction touch = new AndroidTouchAction(driver);
			touch.press(PointOption.point(startX, startY)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
					.moveTo(PointOption.point(endX, endY)).release().perform();
		} catch (Exception e) {
			System.out.println("SwipeLeft Exception::::" + e);
		}
	}

	public static void swipeUp() {
		try {
			Dimension dimension = driver.manage().window().getSize();
			int startX = (int) (dimension.width * 0.5);
			int startY = (int) (dimension.height * 0.2);
			int endX = (int) (dimension.width * 0.5);
			int endY = (int) (dimension.height * 0.8);
			AndroidTouchAction touch = new AndroidTouchAction(driver);
			touch.press(PointOption.point(startX, startY)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
					.moveTo(PointOption.point(endX, endY)).release().perform();
		} catch (Exception e) {
			System.out.println("SwipeUp Exception::::" + e);
		}
	}

	public static void longPress(String info, WebElement web) {
		try {
			AndroidTouchAction touch = new AndroidTouchAction(driver);
			touch.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(web))
					.withDuration(Duration.ofSeconds(2))).release().perform();
		} catch (Exception e) {
			System.out.println("LongPress exception::::" + e);
		}
	}

	public static String getRandomEmail() {
		String chars = "abcdefghijklmnopqrstuvwxyz1234567890";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		while (sb.length() < 10) {
			int index = (int) (random.nextFloat() * chars.length());
			sb.append(chars.charAt(index));
		}
		String resEmail = sb.toString();
		return resEmail + "@mailinator.com";
	}

	public static String getRandomPhNum() {
		String chars = "123456789";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		while (sb.length() <= 9) {
			int index = (int) (random.nextFloat() * chars.length());
			sb.append(chars.charAt(index));
		}
		String resNum = sb.toString();
		return resNum;
	}

	public static String getRandomName() {
		faker = new Faker();
		String name = faker.name().fullName();
		System.out.println(name);
		return name;
	}

	public static void getCurrentDayOfMonth(String day) throws InterruptedException {
		Calendar cal = Calendar.getInstance();
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		int dayToPick = dayOfMonth + 2;
		day = String.valueOf(dayToPick);
		driver.findElement(By.xpath("//*[@text='" + day + "']")).click();
		System.out.println("Element is clicked.");
		Thread.sleep(2000);
	}

	public static String getCurrentTime() {
		ZonedDateTime dateTime = ZonedDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
		return dateTime.format(formatter).toUpperCase();
	}

	public static String validateAppTime(String month) {

		String months = month.substring(23);
		if (months.contains("Sept")) {
			StringBuilder sb = new StringBuilder(months);
			sb = sb.deleteCharAt(3);
			months = sb.toString();
		} else {
			return months;
		}
		return months;
	}

	public static String validateDeviceTime(int value) {
		SimpleDateFormat formated = new SimpleDateFormat("MMM d, yyyy");
		Calendar cal = Calendar.getInstance();
		String dateTextAlone = getCurrentDate();
		try {
			cal.setTime(formated.parse(dateTextAlone));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.add(Calendar.DAY_OF_MONTH, value);
		String dateafter = formated.format(cal.getTime());
		return dateafter;
	}

	public static String getCurrentDate() {
		SimpleDateFormat formated = new SimpleDateFormat("MMM dd, yyyy");
		Date date = new Date();
		String day = formated.format(date);
		return day;
	}

	public static void assertIntEquals(int i, int j, String name) {
		Assert.assertEquals(i, j, name);
	}

	public static void pressBack(WebElement element) throws InterruptedException {
		while (true) {
			try {
				if (element.isDisplayed()) {
					goBack();
				}
			} catch (Exception e) {
				break;
			}
			tempWait(500);
		}
	}

	public static void pressBackUntil(WebElement element) throws InterruptedException {
		while (true) {
			try {
				if (element.isDisplayed()) {
					break;
				}
			} catch (Exception e) {
				goBack();
			}
			tempWait(500);
		}
	}

	public static void log(String comment) {
		ExtentCucumberAdapter.addTestStepLog("***" + comment + "***");
	}

}