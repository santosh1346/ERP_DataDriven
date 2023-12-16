package driverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.SupplierPage;
import config.AppUtil_1;
import utilities.ExcelFileUtil;

public class DriverScript  extends AppUtil_1
{
	String inputpath ="./FileInput/Supplierpage.xlsx";
	String outputpath ="./FileOutPut/Suplierpageresult.xlsx";
    ExtentReports reports;
    ExtentTest test;
	boolean res = false;
	@Test
	public void starttest() throws Throwable
	{
		 reports = new ExtentReports("./target/Supplierpage/addsupplier.html");
		// creat object for exelfile util class
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		//count no of rows in supplier sheet
		int rc = xl.rowCount("supplier");
		Reporter.log("NO f rows are ;;" + rc,true);
		
		for(int i=1;i<=rc;i++)
		{
			test = reports.startTest("Supplier page");
			test.assignAuthor("Santosh");
			test.assignCategory("Suplierdata");
			String suppliername = xl.getCellData("supplier", i, 0); 
			String adress = xl.getCellData("supplier", i, 1);
			String city = xl.getCellData("supplier", i, 2);
			String country = xl.getCellData("supplier", i, 3);
			String contactperson = xl.getCellData("supplier", i, 4);
			String phonenumber = xl.getCellData("supplier", i, 5);
			String email = xl.getCellData("supplier", i, 6);
			String mobilenumber = xl.getCellData("supplier", i, 7);
			String notes = xl.getCellData("supplier", i, 8); 
			
			// call add suplier page class
			
			SupplierPage sp = PageFactory.initElements(driver, SupplierPage.class);
			res = sp.add_Suplier(suppliername, adress, city, country, contactperson, phonenumber, email, mobilenumber, notes);
		   
			if(res)
			{
				xl.setCellData("supplier", i, 9, "Pass", outputpath);
				test.log(LogStatus.PASS, "Login sucess");
			}else
			{
				xl.setCellData("supplier", i, 9, "fail", outputpath);
				test.log(LogStatus.FAIL, "Login failuer");
			}
			reports.endTest(test);
			reports.flush();
		}
		
	}

}
