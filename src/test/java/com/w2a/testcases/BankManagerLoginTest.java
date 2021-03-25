package com.w2a.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.w2a.base.Testbase;


public class BankManagerLoginTest extends Testbase{
	
	@Test
	public void loginAsBankManager() throws InterruptedException, IOException {
		verifyEquals("abc", "xyz");
		//Here we are using hard assertions that means it won't go further it it fails
		log.debug("Inside login Test");
		click("bmlBtn_CSS");
		Assert.assertTrue(isElementPresent(By.cssSelector(ObjectR.getProperty("addCustBtn_CSS"))),"Login not successfull");	
		//Assert.fail("Login is not successfull");
		
	}
}

