package testCases;

import java.util.Locale.Category;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import wrapper.GenericWrapper;
import wrapper.ProjectSpecificWrapper;

public class Create_Lead extends ProjectSpecificWrapper{
	
	@BeforeTest
	public void setData()
	{
		testCaseName="TC001_CreateLead";
		testCaseDesc="Create new Lead on OpenTaps";
		author="Roopa Haribabu";
		category="Sanity Testing";
		excelFileName="Excel1";

	}
	
	
	@Test(dataProvider="fetchData")
	public void createLead(String companyName,String fName, String lName) {
		
		//click Create Lead
		click(locSelector("linktext", "Create Lead"));
		
		//Enter Company name
		clear(locSelector("xpath","//input[@class='inputBox']"));
		append(locSelector("xpath","//input[@class='inputBox']"), companyName);

		//Enter First name
		clear(locSelector("xpath","//input[@id='createLeadForm_firstName']"));
		append(locSelector("xpath","//input[@id='createLeadForm_firstName']"), fName);

		//Enter Last name
		clear(locSelector("xpath","//input[@id='createLeadForm_lastName']"));
		append(locSelector("xpath","//input[@id='createLeadForm_lastName']"), lName);
		
		//Choose Source
		selectDropDownUsingText(locSelector("xpath","//select[@class='inputBox']"),"Employee");
		
		//Choose Marketing Campaign
		selectDropDownUsingText(locSelector("xpath","//select[@name='marketingCampaignId']"),"eCommerce Site Internal Campaign");
		
		//Enter Phone number
		clear(locSelector("xpath","//input[@id='createLeadForm_primaryPhoneNumber']"));
		append(locSelector("xpath","//input[@id='createLeadForm_primaryPhoneNumber']"), "9977366733");
		
		//Enter Email
		clear(locSelector("xpath","(//input[@class='inputBox'])[20]"));
		append(locSelector("xpath","(//input[@class='inputBox'])[20]"), "roopa.sandeep@hotmail.com");

		//Create Lead
		click(locSelector("xpath", "//input[@class='smallSubmit']"));

		//Capture the Lead ID
		String lead_id=getElementText(locSelector("xpath", "//span[@id='viewLead_companyName_sp']"));
		lead_id=lead_id.substring(lead_id.indexOf("(")+1, lead_id.indexOf(")"));
        System.out.println("Lead ID is: "+lead_id);


		}
}

