package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class AppTest extends AppUtil 
{
	String inputpath ="./FileInput/ERPlogindata.xlsx";
	String outputpath ="./FileOutPut/DataDrivenResults.xlsx";
	ExtentReports reports;
	ExtentTest test;
	
	@Test
	public void staTest() throws Throwable
	{
		// define path to generate reports
		reports = new ExtentReports("./target/Reports/Login.html");
		boolean res = false;
		
		//creat object for ExcelFile util class
		
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		
		// count no of rows in login sheet
		
		int rc = xl.rowCount("Login");
		
        // optional 		
		Reporter.log("No of rows in Login Sheet;;" + rc);
		
		// iterate all rows in login sheet
		for(int i = 1; i<=rc;i++)
		{
			test = reports.startTest("Login Test");
			test.assignAuthor("Ranga");
			test.assignCategory("Functional testing");
			// read username and password cell data from excel
			String username = xl.getCellData("Login", i, 0);
			String password = xl.getCellData("Login", i,1);
			
			// call login method
			res = FunctionLibrary.verify_Login(username, password);
			
			//write data in excel  using res
			
			if(res)
			{
				//if res true write as a login sucess into result
				xl.setCellData("Login", i, 2, "Login sucess",outputpath );
				// write pass into status cell
				xl.setCellData("Login", i, 3, "pass", outputpath);
				test.log(LogStatus.PASS, "Login sucess");
				
			}else
			{
				// take screen shot pass
				File screen =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				//copy file into target file folder
		        FileUtils.copyFile(screen, new File("./screenshot/failuer/"+i+"Login Page.png"));
				
				//if res false write as a login fail into result
				xl.setCellData("Login", i, 2, "Login fail",outputpath );
				// write fail into status cell
				xl.setCellData("Login", i, 3, "fail", outputpath);
				test.log(LogStatus.FAIL, "Login Fail");
			}			
	         reports.endTest(test);
	         reports.flush();
	         
		}
		
		
	}

}
