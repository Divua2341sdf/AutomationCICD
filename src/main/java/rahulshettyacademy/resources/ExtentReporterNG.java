package rahulshettyacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReportObject() {  // declared as static so that no need to create object for accessing extent
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter report = new ExtentSparkReporter(path);
		report.config().setReportName("Web Automation Results");
		report.config().setDocumentTitle("Test Results");
		
		//ExtentReports class consolidates test execution i.e it drives test execution
		// extent report accurately reflects the execution of your Selenium tests, including the status of each test case.
		//The extent report will reflect the failure of the second test case after all the test cases have been executed.

		ExtentReports extent = new ExtentReports();  //it consolidates test execution / it drives test execution
		extent.attachReporter(report);    //attach report to your main class extent
		extent.setSystemInfo("Tester", "Rahul Shetty");
		return extent;
	}

}
