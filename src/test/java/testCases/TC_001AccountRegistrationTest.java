package testCases;

import java.time.Duration;

import org.testng.annotations.Test;
import junit.framework.Assert;
import pageObject.AccountRegistrationPage;
import pageObject.HomePage;
import testBase.BaseClass;

public class TC_001AccountRegistrationTest extends BaseClass
{
@Test(groups= {"Regression","Master"})
public void verify_Account_registration() throws InterruptedException 
{       
	try {
	   logger.info("*********starting TC_001AccountRegistrationTest************ ");
	
	
		HomePage hp = new HomePage(driver);
		hp.clickmyaccount();
		logger.info("* click on my account link*");
		hp.clickRegister();
		logger.info("* click on Register link*");
		
		AccountRegistrationPage repage = new  AccountRegistrationPage(driver);
		logger.info("* providing customer details *");
		repage.setFirstName(randomstring().toUpperCase());
		repage.setLastName(randomstring().toUpperCase());
		repage.setEmail(randomstring()+"@gmail.com"); //Randomly generated the email
		repage.setTelephone(randomnumber());
		String password = randomalphanumaric();
		repage.setpassword(password);
		repage.setconfirmpassword(password);
		repage.setprivacypolicy();
		repage.clickcontinuebut();
		Thread.sleep(6000);
		
		
		logger.info("* validating expected messange *");
		String confmsg = repage.getconfirmsg();
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		logger.info("* click on my account link*");
		Thread.sleep(6000);		
}

catch(Exception e )
{
	logger.error("test failed....");
	logger.debug("debug logs..");
	Assert.fail();
}
	 logger.info("*********finished TC_001AccountRegistrationTest************ ");
}
}
