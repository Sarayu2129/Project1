package com.w2a.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.w2a.base.Testbase;

public class TestUtil extends Testbase{

	public static String screenshotPath;
	public static String screenshotName;
	public static  void captureScreenshot() throws IOException{
		 Date d = new Date();
		File scrfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		screenshotName = d.toString().replace(":", "_").replace(" ","_")+".jpg";
		FileUtils.copyFile(scrfile, new File(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\" +screenshotName));
		
	}
	@DataProvider(name = "dp")
	public Object[][] getData(Method m ){//get the method name
		String sheetName= m.getName(); 
		int rowNum= excel.getRowCount(sheetName);
		int colNum = excel.getColumnCount(sheetName);
		Object[][] data = new Object[rowNum-1][colNum];
		
		for (int rows = 2; rows <= rowNum; rows++) {
			for (int cols = 0; cols < colNum; cols++) {

				data[rows - 2][cols] = excel.getCellData(sheetName, cols, rows);

			}
		}
		return data;
		
	}
	
	
	
}
