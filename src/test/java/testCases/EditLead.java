package testCases;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import wrapper.GenericWrapper;
import wrapper.ProjectSpecificWrapper;

public class EditLead extends ProjectSpecificWrapper{
	
	@BeforeTest
	public void setData()
	{
		excelFileName="TC002";

	}
	
	@Test
	public void editLead() { 
		
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
		
		//Enter First name
		clear(locSelector("xpath","(//input[@name='firstName'])[3]"));
		append(locSelector("xpath","(//input[@name='firstName'])[3]"), "Roopa");
		
		//click Find Leads 
		click(locSelector("xpath", "//button[text()='Find Leads']"));

		//click on the First resulting Lead
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		click(locSelector("xpath", "//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a"));
		
		//Verify the title of the page
		boolean title=verifyTitle("View Lead | opentaps CRM");

		//click Edit 
		click(locSelector("xpath", "//a[text()='Edit']"));
		
		//change the Company name
		clear(locSelector("id","updateLeadForm_companyName"));
		append(locSelector("id","updateLeadForm_companyName"), "Infosys Ltd.,");
		
		//click Update
		click(locSelector("xpath", "//input[@class='smallSubmit']"));
		
		//Verify the updated name
		verifyExactText(locSelector("id", "viewLead_companyName_sp"),"Infosys Ltd.,");


		//close the browser
        close();


		
	}
}
