package rahulshettyacademy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{    // ITestListener - interface
	
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); //assign unique thread ID(ErrorValidationTest)->test
	  }

	@Override
	public void onTestSuccess(ITestResult result) {
		 extentTest.get().log(Status.PASS, "Test is passed.");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		  //test.log(Status.FAIL, "Test is failed.");
		  extentTest.get().fail(result.getThrowable());
		  
		  //life of driver
		 try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  
		  String Filepath = null;
		  //Screenshot and attach it to the report
		  try {
			Filepath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //bracket mention testcasename
		  extentTest.get().addScreenCaptureFromPath(Filepath,result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	    // not implemented
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	    // not implemented
	 }

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	    onTestFailure(result);
	}

	@Override
	public void onStart(ITestContext context) {
	    // not implemented
	}

	@Override
	public void onFinish(ITestContext context) {
	    extent.flush();  //it will make sure reports are generated
	}

}
