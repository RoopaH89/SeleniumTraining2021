package testCases;

import org.testng.annotations.Test;

import wrapper.ProjectSpecificWrapper;

public class MergeLead extends ProjectSpecificWrapper{

	@Test
	public void mergeLead(String companyName,String fName, String lName) {
		
		//click Leads link
		click(locSelector("linktext", "Leads"));
		
		//click Find Leads link
		click(locSelector("linktext", "Merge Leads"));
		
		//click on the icon near 'From Lead'
		//click(locSelector("xpath",""))
		
		


	}
	
}
