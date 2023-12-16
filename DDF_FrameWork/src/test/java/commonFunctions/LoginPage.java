package commonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage 
{
//	define Repository for login elements
	@FindBy(xpath = "//button[@id='btnreset']")
	WebElement objreset;
	@FindBy(xpath = "//input[@id='username']")
	WebElement objusername;
	@FindBy(xpath = "//input[@id='password']")
	WebElement objpassword;
	@FindBy(xpath = "//button[@id='btnsubmit']")
	WebElement objlogin;
	
	// method for login
	
	public void loginTest(String username,String password) 
	{
		objreset.click();
		objusername.sendKeys(username);
		objpassword.sendKeys(password);
		objlogin.click();
	}
	
	
	

}
