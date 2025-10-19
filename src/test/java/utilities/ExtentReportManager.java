package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException; 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;


public class ExtentReportManager implements ITestListener
{
	 public ExtentSparkReporter sparkReporter; 
	 public ExtentReports extent;     
	 public ExtentTest test;          
	 String repName;
	  
	  public void onStart(ITestContext testcontext) 
		{
		  SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		  Date dt = new Date();
		  String currentdatetimeStamp = df.format(dt);
		  
		  repName = "Test-Report-" + currentdatetimeStamp + ".html";
		  
		  sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName);
		  sparkReporter.config().setDocumentTitle("Opencart Automation Report"); 
		  sparkReporter.config().setReportName("Opencart Functional Testing");  
		  sparkReporter.config().setTheme(Theme.STANDARD); 
		    
		    
		  extent = new ExtentReports();
		  extent.attachReporter(sparkReporter);
		    
		  extent.setSystemInfo("Application", "opencart");
		  extent.setSystemInfo("Module name", "Admin");
		  extent.setSystemInfo("sub module", "customer");
		  extent.setSystemInfo("User Name", System.getProperty("user.name"));
		  extent.setSystemInfo("Environment", "QA");

		  // Retrieve 'os' and 'browser' parameters from testng.xml and add system info
		  String os = testcontext.getCurrentXmlTest().getParameter("os");
		  String browser = testcontext.getCurrentXmlTest().getParameter("browser");

		  Optional.ofNullable(os).ifPresent(value -> extent.setSystemInfo("Operating System", value));
		  Optional.ofNullable(browser).ifPresent(value -> extent.setSystemInfo("Browser", value));
		   
		  List<String> includedGroups = testcontext.getCurrentXmlTest().getIncludedGroups();
		  if (!includedGroups.isEmpty()) {
			  extent.setSystemInfo("Groups", includedGroups.toString());
		  }
		} 
	  
	  
		public void onTestSuccess(ITestResult result) 
		  {
			test = extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());//to display groups in report
			test.log(Status.PASS, result.getName() + "got successfully executed");
		  }
		
		
		
		public void onTestFailure(ITestResult result) 
		  {
			test = extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());
			
			test.log(Status.FAIL,result.getName() +"Test case got failed:");
			test.log(Status.INFO,result.getThrowable().getMessage());
			
			try {
				String imgPath = new BaseClass().capturescreen(result.getName());
				test.addScreenCaptureFromPath(imgPath);
			}
			catch(Exception e1)
			{
				e1.printStackTrace();
			}
			
		  }
		
		
		
		
		public void onTestSkipped(ITestResult result) 
		  {
			test = extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.SKIP,result.getName() +"Got skipped");
			test.log(Status.INFO,result.getThrowable().getMessage());
		  }
		
		
		
		
		public void onFinish(ITestContext context) 
		  {
			extent.flush();
			
			// Auto-launch the report after all tests are finished
			String pathofExtentReport = System.getProperty("user.dir") +"\\reports\\" + repName;
			File extentReport = new File(pathofExtentReport);
			try {
				Desktop.getDesktop().browse(extentReport.toURI());
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		  }
}
