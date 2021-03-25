package com.w2a.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.base.Testbase;
import com.w2a.utilities.TestUtil;

public class AddCustomerTest extends Testbase{
     
	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
	public void addCustomerTest(String firstname,String lastname,String postcode,String alerttext) throws InterruptedException{
		
		
		//driver.findElement(By.xpath(ObjectR.getProperty("addCustBtn"))).click();
		click("addCustBtn_CSS");
		//driver.findElement(By.cssSelector(ObjectR.getProperty("firstName"))).sendKeys("firstname");
		type("firstName_CSS",firstname);
		
		//driver.findElement(By.cssSelector(ObjectR.getProperty("lastName"))).sendKeys("lastname");
		type("lastName_CSS",lastname);
		
		//driver.findElement(By.cssSelector(ObjectR.getProperty("postCode"))).sendKeys("postcode");
		type("postCode_CSS",postcode);
		
		//driver.findElement(By.cssSelector(ObjectR.getProperty("addBtn"))).click();
		click("addBtn_CSS");   
		Thread.sleep(2000);
		Alert alert = driver.switchTo().alert();
		//Alert alert = wait.until(ExpectedConditions.alertIsPresent()); 
		Assert.assertTrue(alert.getText().contains(alerttext));
		alert.accept();
		Thread.sleep(3000);
		
		
		
	}
	
}
