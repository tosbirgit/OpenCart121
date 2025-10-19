package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage
{
  public HomePage(WebDriver driver) 
  {
	  super(driver);
  }
  
  @FindBy(xpath="//a[@title='My Account']")
  WebElement linkMyaccount;
  
  @FindBy(xpath="//a[text()='Register']")
  WebElement linkRegister;
  
  
  @FindBy(xpath="//a[text()='Login']/parent::li")
  WebElement linklogin;
  
  
  public void clickmyaccount() 
  {
	  linkMyaccount.click();
  }
  public void clickRegister() 
  {
	linkRegister.click();  
  }
  
  public void clicklogin() 
  {
	  linklogin.click(); 
  }
  
  
}
