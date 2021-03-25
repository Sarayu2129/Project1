package com.w2a.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.w2a.utilities.ExcelReader;
import com.w2a.utilities.ExtentReportTest;
import com.w2a.utilities.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Testbase {
	//WebDriver
	 // Logs---log4j jar,.log ,log4j.properties,Logger
	 //ExtentReports
    //JsvaMail
     //Properties
     //DB 
     //Excel
     //ReportNG ,ExtentREports
     // JEnkins
	
	public static WebDriver driver;
	public static Properties p = new Properties();
	public static Properties ObjectR = new Properties();
	public static FileInputStream fis;
	public static Logger log =Logger.getLogger("devpinoyLogger");//this is a standard name for Logger
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait; 
	public ExtentReports Report = ExtentReportTest.getInstance();
	public static ExtentTest test;
	//here in setup method we will start everything
	@BeforeSuite
    public void setUp(){
    	
		if (driver == null){
		
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				p.load(fis);
				log.debug("Config file loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\ObjectRepository.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    try {
				ObjectR.load(fis);
				log.debug("Object Repository  file loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
			
		if(p.getProperty("browser").equals("firefox")){
			WebDriverManager.firefoxdriver().setup();
			System.getProperty("webdriver.firefox.driver",System.getProperty("user.dir"+"\\src\\test\\resources\\executables\\geckodriver.exe")); 
			driver = new FirefoxDriver();
			log.debug("Firefox Browser Launched!!!");
		}else if(p.getProperty("browser").equals("chrome")){
			WebDriverManager.chromedriver().setup();
		System.getProperty("webdriver.chrome.driver",System.getProperty("user.dir"+"\\src\\test\\resources\\executables\\chromedriver.exe")); 
			driver = new ChromeDriver();
			log.debug("Chrome Browser Launched!!!");
		}
    }else if(p.getProperty("browser").equals("ie")){
    	
		WebDriverManager.iedriver().setup();
	System.getProperty("webdriver.ie.driver",System.getProperty("user.dir"+"\\src\\test\\resources\\executables\\IEDriverServer.exe")); 
		driver = new InternetExplorerDriver();
	}
		driver.get(p.getProperty("testsiteurl"));
		log.debug("Navigated to :  "+p.getProperty("testsiteurl"));
		//DbManager.setMysqLDbConnection();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(p.getProperty("implicit.wait")), TimeUnit.SECONDS);
        //wait = new WebDriverWait(driver,Integer.parseInt(p.getProperty("explicit.wait")));
}
public void click(String locator){
	if(locator.endsWith("_CSS")){
		driver.findElement(By.cssSelector(ObjectR.getProperty(locator))).click();
	}else if(locator.endsWith("_XPATH")){
		driver.findElement(By.xpath(ObjectR.getProperty(locator))).click();
	}else if(locator.endsWith("_ID")){
		driver.findElement(By.id(ObjectR.getProperty(locator))).click();
	}
	
	test.log(LogStatus.INFO, "Clicking on :"  +locator);
	
	
	}
	public void type(String locator,String value){
		if(locator.endsWith("_CSS")){
		driver.findElement(By.cssSelector(ObjectR.getProperty(locator))).sendKeys(value);
		}else if(locator.endsWith("_XPATH")){
			driver.findElement(By.xpath(ObjectR.getProperty(locator))).sendKeys(value);
		}else if(locator.endsWith("_ID")){
			driver.findElement(By.id(ObjectR.getProperty(locator))).sendKeys(value);
		}
		test.log(LogStatus.INFO, "Typing in the locator" +locator +"entered the value :"+value);
	}
	static WebElement dropdown;
	public void select(String locator,String value){
		if(locator.endsWith("_CSS")){
			dropdown = driver.findElement(By.cssSelector(ObjectR.getProperty(locator)));
			
		}else if(locator.endsWith("_XPATH")){
			dropdown = driver.findElement(By.xpath(ObjectR.getProperty(locator)));
			
		}else if(locator.endsWith("_ID")){
			dropdown = driver.findElement(By.id(ObjectR.getProperty(locator)));
		}	
	
	 Select select = new Select(dropdown);
	 select.selectByVisibleText(value);
	 test.log(LogStatus.INFO, "Selecting from dropdown" +locator +"value as :"+value);
	}
	public boolean isElementPresent(By by){

		try{
			driver.findElement(by);
			return true;
			
		}catch(NoSuchElementException e ) {
			 	
			return false;
		}
	}
	
	public static void verifyEquals(String expected,String actual) throws IOException{
		try{
			Assert.assertEquals(actual, expected);
		}catch(Throwable t){
			 TestUtil.captureScreenshot();
			 //ReportNG Report
			 Reporter.log("<br>"+ "Verification failed :"+t.getMessage()+"<br>");
			//Reporter.log("<a target =\"blank\" href = \"C:\\Selenium Softwares\\screenshot\\error.jpg\" ><img src = \"C:\\Selenium Softwares\\screenshot\\error.jpg\" height=350 width=400</img></a>");
			Reporter.log("<a target = \"blank\" href = "+TestUtil.screenshotName+"><img src = "+TestUtil.screenshotName+"height=200 width=200></a>"); 
			Reporter.log("<br>");
			Reporter.log("<br>");
			//TestNG Report
			test.log(LogStatus.FAIL,"Verification Failed with Exceptions: "+t.getMessage());
			test.log(LogStatus.FAIL,test.addScreenCapture(TestUtil.screenshotName) ); 
			
			 
			 
		}
	}
	  //here in teardown method we will quit everything
	@AfterSuite
    public void tearDown(){
		if(driver!= null){
		driver.quit();
		}
    	log.debug("Test Execution completed !!!");
    }
}
