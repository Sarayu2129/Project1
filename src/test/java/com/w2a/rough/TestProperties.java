package com.w2a.rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
	public static void main(String[] args) throws IOException {
		
		//System.out.println(System.getProperty("user.dir"));
		Properties p = new Properties();
		//we can give like this because user.dir will be hav ethe path untill project1
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
		p.load(fis);//to load the file
		//this browser we are getting this from config.properties file in that we mention browser = chrome
		System.out.println(p.getProperty("browser"));
	    Properties ObjectR = new Properties();
	    fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\ObjectRepository.properties");
	    
	    ObjectR.load(fis);
	    ObjectR.getProperty("bmlBtn_CSS");
	
	}
}
