package commonFunctions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class SupplierPage {
WebDriver driver;
public SupplierPage(WebDriver driver)
  {
	this.driver=driver;
  }

	// define repository

@FindBy(xpath = "(//a[@href='a_supplierslist.php'])[2]")
WebElement objsupplier;

@FindBy(xpath = "(//span[@data-caption='Add'])[1]")
WebElement objaddicon;

@FindBy(xpath = "//input[@id='x_Supplier_Number']")
WebElement objsuppilernumber;

@FindBy(xpath = "//input[@id='x_Supplier_Name']")
WebElement objsuppilername;

@FindBy(xpath = "//textarea[@id='x_Address']")
WebElement objaddress;

@FindBy(xpath = "//input[@id='x_City']")
WebElement objcity;

@FindBy(xpath ="//input[@id='x_Country']")
WebElement objcountry;

@FindBy(xpath ="//input[@id='x_Contact_Person']")
WebElement objcontactperson;

@FindBy(xpath = "//input[@id='x_Phone_Number']")
WebElement objphonenumber;

@FindBy(xpath = "//input[@id='x__Email']")
WebElement objemail;

@FindBy(xpath = "//input[@id='x_Mobile_Number']")
WebElement objmobilenumber;

@FindBy(xpath = "//textarea[@id='x_Notes']")
WebElement objnote;

@FindBy(id = "btnAction")
WebElement objaddbtn;

@FindBy(xpath = "//button[normalize-space()='OK!']")
WebElement objconfirmokbtn;

@FindBy(xpath = "//button[@class='ajs-button btn btn-primary']")
WebElement objalertokbtn;

@FindBy(xpath = "//span[@data-caption='Search']")
WebElement objsearchpanel;

@FindBy(xpath = "//input[@id='psearch']")
WebElement objsearchtextbox;

@FindBy(xpath = "//button[@id='btnsubmit']")
WebElement objsearchbutton;

@FindBy(xpath = "//table[@class='table ewTable']/tbody/tr[1]/td[6]/div/span/span")
WebElement webtabel;


// method for add supplier

public boolean add_Suplier(String supliername,String adress, String city,String country,
		String contactperson,String phonenumber,String email,String mobilenumber,String notes) throws InterruptedException
   {
	Actions ac = new Actions(driver);
	ac.moveToElement(objsupplier).click().perform();
	Thread.sleep(200);
	ac.moveToElement(objaddicon).click().perform();
	Thread.sleep(200);
	String exp_data = objsuppilernumber.getAttribute("value");
	this.objsuppilername.sendKeys(supliername);
	this.objaddress.sendKeys(adress);
	this.objcity.sendKeys(city);
	this.objcountry.sendKeys(country);
	this.objcontactperson.sendKeys(contactperson);
	this.objphonenumber.sendKeys(phonenumber);
	this.objemail.sendKeys(email);
	this.objmobilenumber.sendKeys(mobilenumber);
	this.objnote.sendKeys(notes);
	Thread.sleep(200);
	
	JavascriptExecutor js = (JavascriptExecutor)driver;
    js.executeScript("window.scrollBy(0,250)");
	
	ac.moveToElement(this.objaddbtn).click().perform();
	//driver.findElement(By.id("btnAction")).click();
	Thread.sleep(2000);
	ac.moveToElement(this.objconfirmokbtn).click().perform();
	Thread.sleep(200);
	ac.moveToElement(objalertokbtn).click().perform();
	
	// if search text box alredy displayed donot click search pannel
	if(!this.objsearchtextbox.isDisplayed())
		
	//click search panel if searchbox is not displayed
		this.objsearchpanel.click();
	
	this.objsearchtextbox.clear();
	this.objsearchtextbox.sendKeys(exp_data);
	this.objsearchbutton.click();
	Thread.sleep(200);
	String act_data =  this.webtabel.getText();
	
	if(exp_data.equals(act_data))
	{
		 Reporter.log("supplier add sucess;;"+exp_data +" "+act_data,true);
		 return true;
	}
	else 
	{
		Reporter.log("supplier add fail;;"+exp_data +" "+act_data,true);
		return false;
	}
	
	}










}
