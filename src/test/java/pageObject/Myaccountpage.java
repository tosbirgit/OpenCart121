package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Myaccountpage extends BasePage{

	public Myaccountpage(WebDriver driver) {
		super(driver);
		
	}

	
@FindBy(xpath="//h2[text()='My Account']") WebElement msgHeading;  //my account page heading
@FindBy(xpath="//div[@class='list-group']//a[text()='Logout']") WebElement lnklogout;
	
	
	public boolean ismyaccountpageexist()
	{
		try 
		{
			return (msgHeading.isDisplayed());
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public void clicklogout()
	{
		lnklogout.click();
	}
}
