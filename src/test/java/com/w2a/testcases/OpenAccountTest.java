package com.w2a.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.base.Testbase;
import com.w2a.utilities.TestUtil;

public class OpenAccountTest extends Testbase{
     
	@Test(dataProviderClass= TestUtil.class,dataProvider="dp")
	public void openAccountTest(String customer,String currency) throws InterruptedException{

		
		click("openaccount_XPATH");
	select("customer_XPATH",customer);
	select("currency_CSS",currency); 
	click("process_XPATH");
	//Alert alert = driver.switchTo().alert();
	Thread.sleep(3000);
	//Alert alert1 = wait.until(ExpectedConditions.alertIsPresent()); 
	Alert alert = driver.switchTo().alert();
	
	alert.accept();
		
	}
	
	
}
