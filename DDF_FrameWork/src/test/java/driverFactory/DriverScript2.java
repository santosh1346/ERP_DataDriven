package driverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.CustomerModule;
import commonFunctions.SupplierPage;
import config.AppUtil_1;
import utilities.ExcelFileUtil;

public class DriverScript2 {
	public class DriverScript  extends AppUtil_1
	{
		String inputpath ="./FileInput/Custmerpage.xlsx";
		String outputpath ="./FileOutPut/Custmerpageresult.xlsx";
	    ExtentReports reports;
	    ExtentTest test;
		boolean res = false;
		@Test
		public void starttest() throws Throwable
		{
			 reports = new ExtentReports("./target/Custmerpage/add.html");
			// creat object for exelfile util class
			ExcelFileUtil xl = new ExcelFileUtil(inputpath);
			//count no of rows in supplier sheet
			int rc = xl.rowCount("customer");
			Reporter.log("NO f rows are ;;" + rc,true);
			
			for(int i=1;i<=rc;i++)
			{
				test = reports.startTest("customer page");
				test.assignAuthor("Santosh");
				test.assignCategory("Custmerdata");
				String suppliername = xl.getCellData("customer", i, 0); 
				String adress = xl.getCellData("customer", i, 1);
				String city = xl.getCellData("customer", i, 2);
				String country = xl.getCellData("customer", i, 3);
				String contactperson = xl.getCellData("customer", i, 4);
				String phonenumber = xl.getCellData("customer", i, 5);
				String email = xl.getCellData("customer", i, 6);
				String mobilenumber = xl.getCellData("customer", i, 7);
				String notes = xl.getCellData("customer", i, 8); 
				
				// call add customer page class
				
				CustomerModule sp = PageFactory.initElements(driver, CustomerModule.class);
				res = sp.add_customer(suppliername, adress, city, country, contactperson, phonenumber, email, mobilenumber, notes);
			   
				if(res)
				{
					xl.setCellData("customer", i, 9, "Pass", outputpath);
					test.log(LogStatus.PASS, "Login sucess");
				}else
				{
					xl.setCellData("customer", i, 9, "fail", outputpath);
					test.log(LogStatus.FAIL, "Login failuer");
				}
				reports.endTest(test);
				reports.flush();
			}
			
		}

	}


}
