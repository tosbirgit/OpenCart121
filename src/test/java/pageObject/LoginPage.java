package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage
{

	public LoginPage(WebDriver driver) 
	{
		super(driver);
		
	}
	
	@FindBy(xpath="//input[@id='input-email']") WebElement textEmail;
	
	@FindBy(xpath="//input[@id='input-password']") WebElement txtPwd;
	
	@FindBy(xpath="//input[@class='btn btn-primary']") WebElement btnlogin;
	
	public void setemail(String email) 
	{
		textEmail.sendKeys(email);
	}
	
	public void setpwd(String pwd) 
	{
		txtPwd.sendKeys(pwd);
	}
	public void clicklogin() 
	{
		btnlogin.click();
	}
}
