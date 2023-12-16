package commonFunctions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class CustomerModule 
{
	WebDriver driver;
	public CustomerModule(WebDriver driver)
	  {
		this.driver=driver;
	  }
@FindBy(xpath = "(//a[@href='a_customerslist.php'])[2]")	
WebElement objcutmer;
	
@FindBy(xpath = "(//span[@data-caption='Add'])[1]")
WebElement objaddicon;

@FindBy(xpath = "//input[@id='x_Customer_Number']")
WebElement objcustomernumber;

@FindBy(xpath = "//input[@id='x_Customer_Name']")
WebElement objcustomername;

@FindBy(xpath = "//textarea[@id='x_Address']")
WebElement objadress;

@FindBy(xpath = "//input[@id='x_City']")
WebElement objcity;

@FindBy(xpath = "//input[@id='x_Country']")
WebElement objcountry;

@FindBy(xpath = "//input[@id='x_Contact_Person']")
WebElement objcontactperson;

@FindBy(xpath = "//input[@id='x_Phone_Number']")
WebElement objphonenumber;

@FindBy(xpath = "//input[@id='x__Email']")
WebElement objemail;

@FindBy(xpath = "//input[@id='x_Mobile_Number']")
WebElement objmobilenumber;

@FindBy(xpath = "//input[@id='x_Notes']")
WebElement objnotes;

@FindBy(id = "btnAction")
WebElement objaddbutton;

@FindBy(xpath = "//button[normalize-space()='OK!']")
WebElement objaddconfmbutton;

@FindBy(xpath = "//button[@class='ajs-button btn btn-primary']")
WebElement objalertokbutton;

@FindBy(xpath = "//table[@class='table ewTable']/tbody/tr[1]/td[5]")
WebElement webtable;

@FindBy(xpath = "//span[@data-caption='Search']")
WebElement objsearchpanel;

@FindBy(xpath = "//input[@id='psearch']")
WebElement objsearchtextbox;

@FindBy(xpath = "//button[@id='btnsubmit']")
WebElement objsearchbutton;



public boolean  add_customer(String customername,String adress, String city, String country,
		String contactperson, String phonenumber , String email, String mobilenumber, String notes) throws InterruptedException
   {
	 Actions ac =  new Actions(driver);
	 ac.moveToElement(objcutmer).click().perform();
	 ac.moveToElement(objaddicon).click().perform();
		Thread.sleep(200);
		String exp_data = objcustomernumber.getAttribute("value");
		this.objcustomername.sendKeys(customername);
		this.objadress.sendKeys(adress);
		this.objcity.sendKeys(city);
		this.objcountry.sendKeys(country);
		this.objcontactperson.sendKeys(contactperson);
		this.objphonenumber.sendKeys(phonenumber);
		this.objemail.sendKeys(email);
		this.objmobilenumber.sendKeys(mobilenumber);
		this.objnotes.sendKeys(notes);
		Thread.sleep(200);
		JavascriptExecutor js = (JavascriptExecutor)driver;
	    js.executeScript("window.scrollBy(0,250)");
		
		ac.moveToElement(this.objaddbutton).click().perform();
		
		Thread.sleep(2000);
		ac.moveToElement(this.objaddconfmbutton).click().perform();
		Thread.sleep(200);
		ac.moveToElement(objalertokbutton).click().perform();
		
		// if search text box alredy displayed donot click search pannel
		if(!this.objsearchtextbox.isDisplayed())
			
		//click search panel if searchbox is not displayed
			this.objsearchpanel.click();
		
		this.objsearchtextbox.clear();
		this.objsearchtextbox.sendKeys(exp_data);
		this.objsearchbutton.click();
		Thread.sleep(200);
		String act_data =  this.webtable.getText();
		
		if(exp_data.equals(act_data))
		{
			 Reporter.log("customer add sucess;;"+exp_data +" "+act_data,true);
			 return true;
		}
		else 
		{
			Reporter.log("customer add fail;;"+exp_data +" "+act_data,true);
			return false;
		}
		
		
		
	 
	}






}
