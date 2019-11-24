package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Login_TechFios {
	WebDriver driver;

	
	@BeforeTest
	public void Launch() {
		
		// Open the Browser
				System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
				driver = new ChromeDriver();
	}
	
	
	@Test(priority = 1)
	public void userShouldBeAbleToLogin() throws InterruptedException {
		
		// Manage the Basic Setup
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		// Open the WebSite
		driver.get("http://techfios.com/test/billing/?ng=login/");

		// Login into WebSite
		driver.findElement(By.xpath("//input[@id=\"username\"]")).sendKeys("techfiosdemo@gmail.com");
		driver.findElement(By.xpath("//input[@id=\"password\"]")).sendKeys("abc123");
		driver.findElement(By.xpath("//button[contains(text(), 'Sign')]")).click();
		
		By DASHBOARD_LOCATOR = By.xpath("//h2[contains(text(),'Dashboard ')]");
		waitForElement(driver,60,DASHBOARD_LOCATOR);
		}
	
	@Test(priority=2)
	public void InvalidLogin() throws InterruptedException {
				// Manage the Basic Setup
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		// Open the WebSite
		driver.get("http://techfios.com/test/billing/?ng=login/");

		// Login into WebSite
		driver.findElement(By.xpath("//input[@id=\"username\"]")).sendKeys("techfiosdemo@gmail.com");
		driver.findElement(By.xpath("//input[@id=\"password\"]")).sendKeys("a123");
		driver.findElement(By.xpath("//button[contains(text(), 'Sign')]")).click();

		By ALERT_MSG_LOCATOR = By.xpath("//div[@class='alert alert-danger fade in']");
		//driver.findElement(ALERT_MSG_LOCATOR).isDisplayed();
		
		waitForElement(driver,10,ALERT_MSG_LOCATOR);
		//Thread.sleep(5000);

	}
	
	@AfterTest
	public void close() 
	{
		driver.close();
		driver.quit();
	}

	
	public void waitForElement(WebDriver driver, int timeInSeconds, By locator) {
		WebDriverWait wait = new WebDriverWait(driver,60);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}
}