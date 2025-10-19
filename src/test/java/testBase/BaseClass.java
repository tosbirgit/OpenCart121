package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;

import org.apache.logging.log4j.LogManager; //Log4J
import org.apache.logging.log4j.Logger;    //Log4J
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


public class BaseClass

{
	public static  WebDriver driver;
	public Logger logger;  //log4J
	public Properties p;
	@BeforeClass(groups= {"Regression","Sanity","Master","Datadriven"})
	@Parameters({"os","browser"})
	
	
	public void setup(String os,String br) throws IOException, InterruptedException
	{
		//loading config.properties file
		
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		
		
		
		
		logger = LogManager.getLogger(this.getClass()); //LOG4J2
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
				{
			      DesiredCapabilities capabilities = new DesiredCapabilities();
			      
			      //os
			      if(os.equalsIgnoreCase("windows")) 
			      {
			      capabilities.setPlatform(Platform.WIN11);
			      }
			      else if(os.equalsIgnoreCase("mac"))
			      {
			    	  capabilities.setPlatform(Platform.MAC); 
			      }
			      else 
			      {
			    	  System.out.println("No Matching OS");
			      }
			      
			      
			      //browser
			      
			      switch (br.toLowerCase())
			      {
			      case "chrome":capabilities.setBrowserName("chrome");
			      break;
			      case "edge":capabilities.setBrowserName("MicrosoftEdge");
			      break;
			      default:System.out.println("no matching browser");
			      return;
			      }
			      driver = new RemoteWebDriver(new URL("http://192.168.100.75:4444/wd/hub"),capabilities);
			      
				}
		
		
		
		if (p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
			case "chrome":driver = new ChromeDriver(); break;
			case "edge":driver = new EdgeDriver(); break;
			case "firfox":driver = new FirefoxDriver();break;
			default: System.out.println("Invalid browser name.."); return;
			}
		}
			      
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get(p.getProperty("appurl")); //reading url from properties file
		driver.manage().window().maximize();
		
		
	}
	@AfterClass(groups= {"Regression","Sanity","Master","Datadriven"})
	public void teardown() 
	{
		driver.quit();
	}
		
  public String randomstring()
		{
			String genstring =RandomStringUtils.randomAlphabetic(5);
			return genstring;	
		}
		public String randomnumber()
		{
			String gennumber =RandomStringUtils.randomNumeric(10);
			return gennumber;	
		}
		public String randomalphanumaric()
		{
			String gennumber =RandomStringUtils.randomNumeric(3);
			String genstring =RandomStringUtils.randomAlphabetic(5);
			return (gennumber + "@#"+ genstring);	
		}
		
		public String capturescreen(String tname) 
		{
			String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			
			
			TakesScreenshot takesScreenshot =(TakesScreenshot) driver;
			File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
			
			String targetFilePath =System.getProperty("user.dir") + "\\screenShots\\"+ tname+"-"+timeStamp+".png";
			File targetFile = new File(targetFilePath);
			
			sourceFile.renameTo(targetFile);
			return targetFilePath;
			
		}
		
}
