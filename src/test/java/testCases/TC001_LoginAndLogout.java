package testCases;

import org.junit.Test;
import org.openqa.selenium.WebElement;

import wrapper.GenericWrapper;

public class TC001_LoginAndLogout extends GenericWrapper{
	
	@Test
	public void login() {
		invokeBrowser("chrome", "http://iarchtaps.com:8080/opentaps/");
		clear(locSelector("id", "username"));
		append(locSelector("id", "username"), "DemoSalesManager");
		clear(locSelector("id", "password"));
		append(locSelector("id", "password"), "crmsfa");
		click(locSelector("xpath", "//input[@class='decorativeSubmit']"));
		
		getElementText(locSelector("xpath", "//div[@id='form']/h2"));
	}

}
