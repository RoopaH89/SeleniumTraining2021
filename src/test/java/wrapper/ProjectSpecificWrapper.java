package wrapper;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import utils.ReadExcel;

public class ProjectSpecificWrapper extends GenericWrapper{
	
	public String excelFileName;

	@Parameters({"url","uName","pwd"})
	@BeforeMethod
	public void login(String url,String userName, String password) {
		invokeBrowser("chrome", url);
		clearAndType(locSelector("id", "username"), userName);
		clearAndType(locSelector("id", "password"), password);
		click(locSelector("class", "decorativeSubmit"));
		click(locSelector("linktext", "CRM/SFA"));
	}

	@AfterMethod
	public void closeApp() {
		close();	
	}
	
	@DataProvider(name="fetchData")
	public String[][] getData() throws IOException
	{
		ReadExcel read=new ReadExcel();
		return read.readExcel(excelFileName);
	
	}

}
