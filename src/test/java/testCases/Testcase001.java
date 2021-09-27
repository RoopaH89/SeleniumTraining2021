package testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Testcase001 {
	
	private static String url="http://iarchtaps.com:8080/opentaps/";
	private static ChromeDriver driver=null;
	private static String username="DemoSalesManager";
	private static String password="crmsfa";


	public static void main(String[] args) 
	{
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		//Launch browser
		driver.get(url);
		
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		//Enter User name and password
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='PASSWORD']")).sendKeys(password);
		
		//Login
		driver.findElement(By.xpath("//input[@class='decorativeSubmit']")).click();
		
		//select CRM/FSA
		driver.findElement(By.xpath("//a[contains(text(),'CRM')]")).click();
		
		//select Create Lead
		driver.findElement(By.xpath("//a [text()='Create Lead']")).click();
		
		//Enter Company name
		driver.findElement(By.xpath("//input[@class='inputBox']")).sendKeys("Tata Consultancy Services");

		//Enter First name
		driver.findElement(By.xpath("//input[@id='createLeadForm_firstName']")).sendKeys("Adithya");

		//Enter Last name
		driver.findElement(By.xpath("//input[@id='createLeadForm_lastName']")).sendKeys("Ranjan");

		
		//Choose Source
		
		WebElement web_element1 = driver.findElement(By.xpath("//select[@name='dataSourceId']"));
		Select source=new Select(web_element1);
		source.selectByValue("LEAD_EMPLOYEE");
		
		//Choose Marketing Campaign
		
		
		WebElement web_element2 = driver.findElement(By.xpath("//select[@name='marketingCampaignId']"));
		Select marketing_campaign=new Select(web_element2);
		marketing_campaign.selectByIndex(4);
		
		//Enter Phone number
		driver.findElement(By.xpath("//input[@id='createLeadForm_primaryPhoneNumber']")).sendKeys("34445456");

		//Enter Email
		driver.findElement(By.xpath("(//input[@class='inputBox'])[20]")).sendKeys("eee.66@gmail.com");

		//Create Lead
		driver.findElement(By.xpath("//input[@class='smallSubmit']")).click();
		
		//Capture the Lead ID
		WebElement leadID = driver.findElement(By.xpath("//span[@id='viewLead_companyName_sp']"));
        String actual_text=leadID.getText();
        actual_text=actual_text.substring(actual_text.indexOf("(")+1, actual_text.indexOf(")"))	;
        System.out.println("Lead ID is: "+actual_text);
        

        //close the browser
        driver.close();
        }

}
