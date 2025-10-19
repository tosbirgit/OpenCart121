package testCases;

import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.Myaccountpage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_loginDDT_test extends BaseClass 
{
	@Test(dataProvider="LoginData",dataProviderClass= DataProviders.class,groups= {"Datadriven","Master"}) //getting dataprovider from different class
	public void verify_loginDDT(String email,String pwd,String exp)
	{
		logger.info("************starting execution TC_003_LoginDDT **************************");
		
		
		
		try {
			//home page
			HomePage ph = new HomePage(driver);
			ph.clickmyaccount();
			ph.clicklogin();
			
			
			//login page
			LoginPage pl = new LoginPage(driver);
			pl.setemail(email);
			pl.setpwd(pwd);
			pl.clicklogin();
			
			//my Account page
			Myaccountpage macp = new Myaccountpage(driver);
			boolean targetpage = macp.ismyaccountpageexist();
			
			/* 
			 data is valid--login successfull - test pass
			 data is valid--login failed -       test failed   
			 
			  data is invalid--login sucessfull -    test failed 
			  data is invalid--login failed -       test passed  
			 */
			
			
			
			if(exp.equalsIgnoreCase("valid"))
			{
				if(targetpage==true)
				{
					macp.clicklogout();
					Assert.assertTrue(true);
					
				}
				else 
				{
					Assert.assertTrue(false);
				}
				
				
			}
			
			if(exp.equalsIgnoreCase("invalid"))
			{
				if(targetpage==true)
				{
					macp.clicklogout();
					Assert.assertTrue(false);
					
				}
				else 
				{
					Assert.assertTrue(true);
				}
				
				
			}
			
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("************Finishing execution TC_003_LoginDDT **************************");	
			
			
		 }
}
