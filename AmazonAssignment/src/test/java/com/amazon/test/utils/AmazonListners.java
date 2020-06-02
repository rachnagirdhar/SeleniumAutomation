package com.amazon.test.utils;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.amazon.base.AmazonBaseClass;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class AmazonListners extends AmazonBaseClass implements ITestListener {

	ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {

		test = extent.createTest(result.getMethod().getMethodName());
		threadLocal.set(test);

	}

	public void onTestSuccess(ITestResult result) {

		threadLocal.get().log(Status.PASS,
				"Execution for scenario " + result.getMethod().getMethodName() + " successfully done");

	}

	public void onTestFailure(ITestResult result) {

		threadLocal.get().log(Status.FAIL,
				"Something is wrong with " + result.getMethod().getMethodName() + " scenario");

		WebDriver driver = null;

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
			threadLocal.get().addScreenCaptureFromPath((takeScreenShot(result.getMethod().getMethodName(), driver)),
					result.getMethod().getMethodName());
		} catch (IOException e) {

			log.info(e);
		} catch (Exception e) {

			log.info(e);
		}

	}

	public void onStart(ITestContext context) {

		extent = amazonReports();

	}

	public void onFinish(ITestContext context) {

		extent.flush();

	}

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

}
