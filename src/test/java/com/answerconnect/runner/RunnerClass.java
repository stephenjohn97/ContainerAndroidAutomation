package com.answerconnect.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(tags = "@AC_Android", glue = { "com.answerconnect.stepdefinitions" }, features = {
		"src/test/resources/Features/AC_appium.feature" }, plugin = {
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }, dryRun = false, monochrome = true)

public class RunnerClass {

}
