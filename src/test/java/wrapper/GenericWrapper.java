package wrapper;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Reports;

public class GenericWrapper extends Reports{

	
	public static RemoteWebDriver driver;
	
	/**
	 * This method will launch any browser, load the url, 
	 * maximize the browser and set the wait for 30 seconds
	 * 
	 * @param browser 	- This will load the specified browser
	 * @param url 		- This will load the specified url 
	 * @throws WebDriverException 
	 */
	// Launch the browser
	public void invokeBrowser(String browser, String url) {
		if(browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();	
		} else if(browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if(browser.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver= new EdgeDriver();
		}
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
		reportStep("The browser "+browser+" launched successfully","PASS");
	}
	
	/**
	 * This method will find the element using any given Selector.
	 * 
	 * @param selType  	- The selector by which the element to be found
	 * @param selValue 	- The selector value by which the element to be found
	 * @throws NoSuchElementException
	 * @return The first matching element on the current context.**/
	
	
	public WebElement locSelector(String selectorType, String selValue) {
		try {
			switch (selectorType) {
			case "id":             return driver.findElement(By.id(selValue));
			case "name":           return driver.findElement(By.name(selValue));
			case "class":          return driver.findElement(By.className(selValue));
			case "linktext":       return driver.findElement(By.linkText(selValue));
			case "partiallinktext":return driver.findElement(By.partialLinkText(selValue));
			case "css":    return driver.findElement(By.cssSelector(selValue));
			case "tagname": return driver.findElement(By.tagName(selValue));
			case "xpath":          return driver.findElement(By.xpath(selValue));

			}
		}
		catch (NoSuchElementException  e) 
		{
			reportStep("Element not found","FAIL");		
		}
		return null;
	}
	
	/**
	 * This method will find all the matching elements using any given Selector.
	 * 
	 * @param selType  		- The selector by which the element to be found
	 * @param selValue 		- The selector value by which the element to be found
	 * @author Anish 		- PreachTechs
	 * 
	 * @return A list of all WebElements, or an empty list if nothing matches.
	 */
	public List<WebElement> locateElements(String selType, String selValue) {
		try {
			switch (selType) {
			case "id":             return driver.findElements(By.id(selValue));
			case "name":           return driver.findElements(By.name(selValue));
			case "class":          return driver.findElements(By.className(selValue));
			case "linktext":       return driver.findElements(By.linkText(selValue));
			case "partiallinktext":return driver.findElements(By.partialLinkText(selValue));
			case "css": return driver.findElements(By.cssSelector(selValue));
			case "tagname": return driver.findElements(By.tagName(selValue));		
			case "xpath":          return driver.findElements(By.xpath(selValue));

			}
		} 
		catch (NoSuchElementException e) 
		{
			
			reportStep("Element not found","FAIL");		
		}
		return null;	
		}
	
	/**
	 * This method will enter the value in the given text field 
	 * 
	 * @param ele   	- The Webelement (text field) in which the data to be entered
	 * @param data  	- The data to be sent to the webelement
	 * 
	 * @throws ElementNotInteractable,IllegalArgumentException(throws if keysToSend is null)	
	 */
	public void append(WebElement ele, String data) 
	{
		try {
			ele.sendKeys(data);
			reportStep("The data "+data+" entered Successfully","PASS");
		} 
		catch (ElementNotInteractableException e) {
			// TODO Auto-generated catch block
			reportStep("Element not interactable", "FAIL");		
		}
		catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			reportStep("IllegalArgumentException","FAIL");		
		}
	}
	
	
	/**
	 * This method will clear the value in the given text field 
	 * 
	 * @param ele   	- The Webelement (text field) in which the data to be entered
	 * @author Anish 	- PreachTechs
	 * 
	 * @throws InvalidElementStateException	(throws if not user-editable element)	 
	 */
	public void clear(WebElement ele) 
	{
		try {
			ele.clear();
			reportStep("This field is cleared","PASS");
		} catch (InvalidElementStateException e) {
			// TODO Auto-generated catch block
			reportStep("InvalidElementStateException","FAIL");		
		}
	}
	
	public void clearAndType(WebElement ele, String data) {
		try {
			ele.clear();
			ele.sendKeys(data);
			reportStep("The Data "+data+" entered Successfully","PASS");
		} catch (NullPointerException e) {
			reportStep("Null value entered","FAIL");		
		}
	}
	
	/**
	 * This method will click the element and take snap
	 * 
	 * @param ele   	- The Webelement (button/link/element) to be clicked
	 * @author Anish 	- PreachTechs
	 * 
	 * @throws StaleElementReferenceException
	 */
	public void click(WebElement ele) {
		String text=null;
		try {
			text = ele.getText();
			ele.click();
			reportStep("The Element "+text+" clicked Successfully","PASS");
		} catch (StaleElementReferenceException e) {
			reportStep("The Element "+text+" is not clicked","FAIL");
		}
	
	}
	
	public void clickWithNoSnap(WebElement ele) {	
		try {
			ele.click();
			reportStep("The Element "+ele+" clicked","PASS");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			reportStep("The Element is not clicked","FAIL");
		}
	}

	
	/**
	 * This method will get the visible text of the element
	 * 
	 * @param ele   	- The Webelement (button/link/element) in which text to be retrieved
	 */
	public String getElementText(WebElement ele) {
		String text = null;
		try {
			text = ele.getText();
			reportStep("The text is:  "+text +" ","PASS");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			reportStep("Couldn't get the text","FAIL");
		}
		return text;
	}	
	
	
	
	
	/**
	 * This method will get the Color values of the element
	 * 
	 * @param ele   	- The Webelement (button/link/element) in which text to be retrieved
	 * 
	 * @return The visible text of this element.
	 */
	public String getBackgroundColor(WebElement ele)
	{
		String cssValue = null;
		try {
			cssValue = ele.getCssValue("color");
			reportStep("The cssValue "+cssValue+" returns Successfully", "PASS");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			reportStep("Couldn't get the CSS value","FAIL");
		}
		return cssValue;		
	}
	
	/**
	 * This method will get the text of the element textbox
	 * 
	 * @param ele   	- The Webelement (button/link/element) in which text to be retrieved
	 * 
	 * @return The attribute/property's current value (or) null if the value is not set.
	 */
	public String getTypedText(WebElement ele) {
		String attributeValue = null;
		try {
			attributeValue = ele.getAttribute("value");
			reportStep("The attributeValue "+attributeValue+" returned Successfully", "PASS");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			reportStep("Couldn't get the attribute value","FAIL");
		}
		return attributeValue;	}
	/**
	 * This method will select the drop down visible text
	 * 
	 * @param ele   	- The Webelement (dropdown) to be selected
	 * @param value 	- The value to be selected (visibletext) from the dropdown
	 * @throws NoSuchElementException
	 */
	public void selectDropDownUsingText(WebElement ele, String value) 
	{
		try {
			Select select=new Select(ele);
			select.selectByVisibleText(value);
			reportStep("The value "+value+" is chosen successfully","PASS");
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			reportStep("Dropdown is not selected","FAIL");
		}
	}
	
	
	/**
	 * This method will select the drop down using index
	 * 
	 * @param ele   	- The Webelement (dropdown) to be selected
	 * @param index 	- The index to be selected from the dropdown
	 * 
	 * @throws NoSuchElementException
	 */
	public void selectDropDownUsingIndex(WebElement ele, int index) 
	{
		try {
			Select select=new Select(ele);
			select.selectByIndex(index);
			reportStep("The dropdown at index "+index+" is chosen successfully","PASS");
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			reportStep("Dropdown is not selected","FAIL");
		}
		
	}
	
	/**
	 * This method will select the drop down using Value
	 * 
	 * @param ele   	- The Webelement (dropdown) to be selected
	 * @param value 	- The value to be selected (value) from the dropdown 
	 * @author Anish 	- 	PreachTechs
	 * 
	 * @throws NoSuchElementException
	 */
	public void selectDropDownUsingValue(WebElement ele, String value)
	{
		try {
			Select select=new Select(ele);
			select.selectByValue(value);
			reportStep("The value "+value+" is chosen successfully","PASS");

		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			reportStep("Dropdown is not selected","FAIL");
		}
	}
	
	/**
	 * This method will verify exact given text with actual text on the given element
	 * 
	 * @param ele   		- The Webelement in which the text to be need to be verified
	 * @param expectedText  - The expected text to be verified
	 * @author Anish 		- PreachTechs
	 * 
	 * @return true if the given object represents a String equivalent to this string, false otherwise
	 */
	public boolean verifyExactText(WebElement ele, String expectedText)
	{
		boolean bReturn=false;
		if(ele.getText().equals(expectedText))
		{
			reportStep("The expected text contains the actual"+expectedText,"PASS");
			bReturn = true;
			
		}
		else
		{
			reportStep("The expected text doesn't contain the actual"+expectedText,"FAIL");

		}
		return bReturn;

	}
	
	/**
	 * This method will verify given text contains actual text on the given element
	 * 
	 * @param ele  			- The Webelement in which the text to be need to be verified
	 * @param expectedText  - The expected text to be verified
	 * 
	 * @return true if this String represents the same sequence of characters as the specified string, false otherwise
	 */
	public boolean verifyPartialText(WebElement ele, String expectedText) {
		boolean bReturn = false;
		if(ele.getText().contains(expectedText)) {
			reportStep("The expected text contains the actual"+expectedText,"PASS");
			bReturn = true;
		}else {
			reportStep("The expected text doesn't contain the actual"+expectedText,"FAIL");
		}
		return bReturn;
	}
	/**
	 * This method will verify exact given attribute's value with actual value on the given element
	 * 
	 * @param ele   	- The Webelement in which the attribute value to be need to be verified
	 * @param attribute - The attribute to be checked (like value, href etc)
	 * @param value  	- The value of the attribute
	 * 
	 * @return true if this String represents the same sequence of characters as the specified value, false otherwise
	 */
	public boolean verifyExactAttribute(WebElement ele, String attribute, String value) {
		boolean bReturn = false;
		if(ele.getAttribute(attribute).equals(value)) {
			reportStep("The expected attribute :"+attribute+" value contains the actual"+value,"PASS");
			bReturn = true;
		}else {
			reportStep("The expected attribute value does not contain the actual value","FAIL");
		}
		return bReturn;
		
	}
	
	public boolean verifyPartialAttribute(WebElement ele, String attribute, String value) {
		boolean bReturn = false;
		if(ele.getAttribute(attribute).contains(value)) {
			reportStep("The expected attribute :"+attribute+" value contains the actual "+value,"PASS");
			bReturn = true;
		}else {
			reportStep("The expected attribute value does not contain the actual value","FAIL");
		}
		return bReturn;
	}	
	
	/**
	 * This method will verify if the element is visible in the DOM
	 * 
	 * @param ele   	- The Webelement to be checked
	 * 
	 * @return true if the element is displayed or false otherwise
	 */
	public boolean verifyDisplayed(WebElement ele) {
		boolean bReturn = false;
		if(ele.isDisplayed()) {
			reportStep("The element "+ele+" is visible","PASS");
			bReturn = true;
		} else {
			reportStep("The element "+ele+" is not visible","FAIL");
		}
		return bReturn;
	}
	
	/**
	 * This method will verify if the input element is Enabled
	 * 
	 * @param ele   	- The Webelement (Radio button, Checkbox) to be verified
	 * @return true 	- if the element is enabled else false
	 * @author Anish 	- PreachTechs
	 * 
	 * @return True if the element is enabled, false otherwise.
	 */
	public boolean verifyEnabled(WebElement ele) {
		boolean bReturn = false;
		if(ele.isEnabled()) {
			reportStep("The element "+ele+" is Enabled","PASS");
			bReturn = true;
		} else {
			reportStep("The element "+ele+" is not Enabled","FAIL");
		}
		return bReturn;
	}
	
	
	
	/**
	 * This method will verify if the element (Radio button, Checkbox) is selected
	 * 
	 * @param ele   	- The Webelement (Radio button, Checkbox) to be verified
	 * @author Anish 	- PreachTechs
	 * 
	 * @return True if the element is currently selected or checked, false otherwise.
	 */
	public boolean verifySelected(WebElement ele) {
		boolean bReturn = false;
		if(ele.isSelected()) {
			reportStep("The element "+ele+" is selected","PASS");
			bReturn = true;
		} else {
			reportStep("The element "+ele+" is not selected","FAIL");
		}
		return bReturn;
	}

	/**
	 * This method will switch to the Alert
	 * 
	 * @author Anish - PreachTechs
	 * 
	 * @throws NoAlertPresentException
	 */
	public void switchToAlert() {
		try {
			driver.switchTo().alert();
			reportStep("Control switch from HTML to alert","PASS");
		} catch (NoAlertPresentException e) {
			// TODO Auto-generated catch block
			reportStep("Alert not found","FAIL");
		}
	}	
	/**
	 * This method will accept the alert opened
	 * 
	 * @author Anish - PreachTechs
	 * 
	 * @throws NoAlertPresentException
	 */
	public void acceptAlert()
	{
		try {
			Alert alert = driver.switchTo().alert();
			String text = alert.getText();
			alert.accept();
			reportStep("The alert "+text+" is accepted.","PASS");
		}
		catch (NoAlertPresentException e) {
			reportStep("Alert is not displayed","FAIL");
		}
	}
	
	
	/**
	 * This method will dismiss the alert opened
	 * 
	 * @author Anish 	- PreachTechs
	 * 
	 * @throws NoAlertPresentException
	 */
	public void dismissAlert()
	{
		try {
			Alert alert = driver.switchTo().alert();
			String text = alert.getText();
			alert.dismiss();
			reportStep("The alert "+text+" is accepted","PASS");
		} catch (NoAlertPresentException e) {
			// TODO Auto-generated catch block
			reportStep("Alert is not displayed","FAIL");
		}
	}
	
	
	/**
	 * This method will return the text of the alert
	 * 
	 * 
	 * @throws NoAlertPresentException
	 */
	public String getAlertText() {
		String text = null;
		try {
			text = driver.switchTo().alert().getText();
			reportStep("Control switch from HTML to alert and fetched the Text","PASS");
		} catch (NoAlertPresentException e) {
			// TODO Auto-generated catch block
			reportStep("Alert is not displayed","FAIL");
		}
		return text;

	}
	
	/**
	 * This method will enter the value in the alert
	 * 
	 * @param  data		- the data to be entered in alert
	 * 
	 * @throws NoAlertPresentException
	 */
	public void enterAlertText(String data)
	{
		try {
			driver.switchTo().alert().sendKeys(data);
			reportStep("Control switch from HTML to alert and entered the given data ","PASS");
		} catch (NoAlertPresentException e) {
			// TODO Auto-generated catch block
			reportStep("Alert is not displayed","FAIL");
		}
	}

	
	/**
	 * This method will switch to the Window of interest
	 * 
	 * @param index The window index to be switched to. 0 -> first window 
	 * 
	 * @throws NoSuchWindowException
	 */
	public void switchToWindow(int index)
	{
		try {
			Set<String> windows = driver.getWindowHandles();
			List<String> ls = new ArrayList<String>(windows);
			driver.switchTo().window(ls.get(index));
			reportStep("The Window is switched successfully","PASS");
		} catch (NoSuchWindowException e) {
			// TODO Auto-generated catch block
			reportStep("Window not found","FAIL");
		}
	}


	/**
	 * This method will switch to the specific frame using index
	 * 
	 * @param  index   	- The int (frame) to be switched
	 * 
	 * @throws NoSuchFrameException 
	 */
	public void switchToFrame_using_index(int index) {
		try {
			driver.switchTo().frame(index);
			reportStep("The frame is switched successfully","PASS");
		} catch (NoSuchFrameException e) {
			reportStep("Frame not found","FAIL");
		}
	}	
	
	
	/**
	 * This method will switch to the specific frame
	 * 
	 * @param  ele   	- The Webelement (frame) to be switched
	 * 
	 * @throws NoSuchFrameException, StaleElementReferenceException 
	 */
	public void switchToFrame_using_webElement(WebElement ele) {
		try {
			driver.switchTo().frame(ele);
			reportStep("The frame is switched successfully","PASS");
		} 
		catch (NoSuchFrameException e) {
			reportStep("Frame not found","FAIL");
		}
		catch (StaleElementReferenceException e) {
			reportStep("Frame not found","FAIL");
		}

	}
	
	/**
	 * This method will switch to the specific frame using Id (or) Name
	 * 
	 * @param idOrName   - The String (frame) to be switched
	 * 
	 * @throws NoSuchFrameException 
	 */
	public void switchToFrame_using_idOrName(String idOrName) {
		try {
			driver.switchTo().frame(idOrName);
			reportStep("The frame using idOrName is switched successfully","PASS");
		} catch (NoSuchFrameException e) {
			reportStep("Frame not found","FAIL");
		}
	}

	/**
	 * This method will switch to the first frame on the page
	 * 
	 * 
	 * @return This driver focused on the top window/first frame.
	 */
	public void switchOutFrame()
	{
		try {
			driver.switchTo().defaultContent();
			reportStep("Came out from all frames Successfully","PASS");	

		} catch (Exception e) {
			// TODO Auto-generated catch block
			reportStep("Cannot switch out of Frames","FAIL");
		}
	}

	
	/**
	 * This method will verify browser actual url with expected
	 * 
	 * @param url   	- The url to be checked
	 * 
	 * @return true if the given object represents a String equivalent to this url, 
	 * false otherwise
	 */
	public boolean verifyUrl(String url)
	{
		boolean url_bool = false;
		if (driver.getCurrentUrl().equals(url)) {
			reportStep("The url matched successfully","PASS");
			url_bool = true;
		} else {
			reportStep("The url does not match","FAIL");
		}
		return url_bool;
		
	}

	/**
	 * This method will verify browser actual title with expected
	 * 
	 * @param title 	- The expected title of the browser
	 * 
	 * @return true if the given object represents a String equivalent to this title, 
	 * false otherwise
	 */
	public boolean verifyTitle(String title)
	{
		boolean title_bool = false;
		if (driver.getTitle().equals(title)) {
			reportStep("Page title matched successfully","PASS");
			title_bool = true;
		} else {
			reportStep("Page title does not match","FAIL");
		}
		return title_bool;		
	}

	/**
	 * This method will close the active browser
	 * 
	 */
	public void close() {
		try {
			driver.close();
			reportStep("Browser closed Successfully","PASS");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			reportStep("Browser not closed","FAIL");
		}
	}

	/**
	 * This method will close all the browsers
	 * 
	 */
	public void quit()
	{
		try {
			driver.close();
			reportStep("Quit all opened browsers Successfully","PASS");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			reportStep("Browser not closed","FAIL");
		}
	}

}