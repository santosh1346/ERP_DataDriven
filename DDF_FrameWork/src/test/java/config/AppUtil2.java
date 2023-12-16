package config;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import commonFunctions.LoginPage;
import commonFunctions.Logoutpage;

public class AppUtil2 {
	public static WebDriver driver;
	@BeforeTest
	public static void setUp()
	   {
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("http://webapp.qedgetech.com/login.php");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		//call login page class
		
	     LoginPage login = PageFactory.initElements(driver, LoginPage.class);	
	     
	     //call login method
	     login.loginTest("admin", "master");
		}

	@AfterTest
	public void teardown()
	    {
		Logoutpage logout = PageFactory.initElements(driver, Logoutpage.class);
		logout.Logout_App();
		driver.quit();
		}
	}



