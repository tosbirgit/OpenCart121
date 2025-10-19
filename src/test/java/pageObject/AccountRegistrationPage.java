package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) 
	{
		super(driver);
		
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement textFirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement textLastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement textemail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement textTelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement textpassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement textpasswordconfirm;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement checkedpolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btncontinue;
	
	@FindBy(xpath="//h1[text()='Your Account Has Been Created!']")
	WebElement confirmmeg;
	
	public void setFirstName(String firstname) 
	{
		textFirstName.sendKeys(firstname);
	}
	
	public void setLastName(String Lastname) 
	{
		textLastName.sendKeys(Lastname);
	}
	
	public void setEmail(String Email) 
	{
		textemail.sendKeys(Email);
	}
	
	public void setTelephone(String telephone) 
	{
		textTelephone.sendKeys(telephone);
	}
	
	public void setpassword(String pwd) 
	{
		textpassword.sendKeys(pwd);
	}
	
	public void setconfirmpassword(String cnpwd) 
	{
		textpasswordconfirm.sendKeys(cnpwd);
	}
	public void setprivacypolicy() 
	{
		checkedpolicy.click();
	}
	public void clickcontinuebut() 
	{   //solution-1
		btncontinue.click();
	}
	
	public String getconfirmsg() 
	{
		try 
		{
			return(confirmmeg.getText());
		}
		catch (Exception e)
		{
			return(e.getMessage());
		}
		
	}

}
