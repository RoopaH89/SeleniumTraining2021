package testCases;

import org.junit.Test;
import org.testng.annotations.BeforeTest;

import wrapper.GenericWrapper;
import wrapper.ProjectSpecificWrapper;

public class Delete_Lead extends ProjectSpecificWrapper{
	
	@BeforeTest
	public void setData()
	{
		excelFileName="TC003";

	}
	
@Test
public void deleteLead() {
		
		//Launch the browser
		invokeBrowser("chrome", "http://iarchtaps.com:8080/opentaps/");
		
		//Enter the user name
		clear(locSelector("id", "username"));
		append(locSelector("id", "username"), "DemoSalesManager");
		
		//Enter the password
		clear(locSelector("id", "password"));
		append(locSelector("id", "password"), "crmsfa");
		
		//click Login
		click(locSelector("xpath", "//input[@class='decorativeSubmit']"));
		
		//click CRM/SFA link
		click(locSelector("linktext", "CRM/SFA"));
		
		//click Leads link
		click(locSelector("linktext", "Leads"));
		
		//click Find Leads link
		click(locSelector("linktext", "Find Leads"));
		
		//Click Phone tab
		click(locSelector("xpath", "(//span[@class='x-tab-strip-text '])[2]"));

		
		//Enter Phone number
		/*clear(locSelector("xpath","//input[@name='phoneCountryCode']"));
		append(locSelector("xpath","//input[@name='phoneCountryCode']"), "1");
		
		clear(locSelector("xpath","//input[@name='phoneAreaCode']"));
		append(locSelector("xpath","//input[@name='phoneAreaCode']"), "830");*/
		
		clear(locSelector("xpath","//input[@name='phoneNumber']"));
		append(locSelector("xpath","//input[@name='phoneNumber']"), "9977366733");
		
		//click Find Leads 
		click(locSelector("xpath", "//button[text()='Find Leads']"));
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Capture the ID of the first Lead
		String text=getElementText(locSelector("xpath", "//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a"));
		System.out.println("Lead id to be deleted is "+text);

		
}

}
