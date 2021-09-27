package utils;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reports {

		public ExtentReports extent;
		public ExtentSparkReporter spark;
		public ExtentTest test;
		
		public String testCaseName,testCaseDesc,author,category;



		@BeforeSuite
		public void startReports()
		{
			//File Level
			spark= new ExtentSparkReporter("./reports/result.html");
			extent=new ExtentReports();
			extent.attachReporter(spark);
		}
		
		@BeforeClass
		public void startTestCase()
		{
		test = extent.createTest(testCaseName, testCaseDesc);
		test.assignAuthor(author);
		test.assignCategory(category);
		}
		
		
		public void reportStep(String desc, String status) {
			if (status.equalsIgnoreCase("PASS"))
			{
				test.pass(desc);
			}
			else if(status.equalsIgnoreCase("FAIL"))
			{
				test.fail(desc);

			}
		}
		@AfterSuite
		public void stopReports()
		{
		extent.flush();

		}

	}


