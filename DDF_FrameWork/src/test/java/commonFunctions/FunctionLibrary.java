package commonFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary extends AppUtil {
//method for login
	public static boolean verify_Login(String user,String pass) throws Throwable
	{
		driver.get(conpro.getProperty("url"));
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath(conpro.getProperty("objreset"))).click();
		driver.findElement(By.xpath(conpro.getProperty("objuser"))).sendKeys(user);
		driver.findElement(By.xpath(conpro.getProperty("objpass"))).sendKeys(pass);
		driver.findElement(By.xpath(conpro.getProperty("objlogin"))).click();
		Thread.sleep(3000);
		String expected ="dashboard";
		String actual =driver.getCurrentUrl(); 
		if(actual.contains(expected))
		{
			driver.findElement(By.xpath(conpro.getProperty("objlogout"))).click();
			Reporter.log("Login sucess::"+"  "+expected+"  "+actual,true);
			return true;
		}else
		{
			// capture error message
			String  message = driver.findElement(By.xpath(conpro.getProperty("objerrormessaage"))).getText();
			driver.findElement(By.xpath(conpro.getProperty("objok"))).click();
			Reporter.log(message,true);
			return false;	
		} 
	}
}
