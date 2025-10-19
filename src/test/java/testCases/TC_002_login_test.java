package testCases;

import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.Myaccountpage;
import testBase.BaseClass;

public class TC_002_login_test extends BaseClass
{
	
@Test(groups= {"Sanity","Master"})
 public void verify_login() throws InterruptedException
 {
	try {
	//home page
	logger.info("*********Starting TC_002_login_test ************* ");
	HomePage hp = new HomePage(driver);
	hp.clickmyaccount();
	hp.clicklogin();
	
	
	//login page
	LoginPage lp = new LoginPage(driver);
	lp.setemail(p.getProperty("email"));
	lp.setpwd(p.getProperty("password"));
	lp.clicklogin();
	
	//my Account page
	Myaccountpage macp = new Myaccountpage(driver);
	boolean targetpage = macp.ismyaccountpageexist();
	Assert.assertTrue(targetpage);
	logger.info("*********Finished TC_002_login_test ************* ");
	}
	catch(Exception e)
	{
		Assert.fail();
	}
	Thread.sleep(10000);
 }
	
	
}
