package com.netbanking.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.netbanking.utilities.ReadConfig;

public class BaseClass
{
	ReadConfig readconfig=new ReadConfig();
public String baseUrl=readconfig.getApplicationURL();
public String username=readconfig.getUserName();
public String password=readconfig.getPassword();
public static WebDriver driver;

public static Logger logger;
@Parameters("browser")
@BeforeClass
public void setUp(String br)
{
	logger=Logger.getLogger("ebanking");
	PropertyConfigurator.configure("Log4j.properties");
	if(br.equals("chrome"))
	{
		System.setProperty("webdriver.chrome.driver", readconfig.getChromepath());
		driver=new ChromeDriver();
	}
	else if(br.equals("ie"))
	{
		System.setProperty("webdriver.ie.driver", readconfig.getIEpath() );
		driver=new InternetExplorerDriver();
	}
	else if(br.equals("firefox"))
	{
		System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxpath());
		driver=new FirefoxDriver();
	}
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	driver.get(baseUrl);
}
@AfterClass
public void tearDown()
{
	driver.quit();
}
public void captureScreen(WebDriver driver,String tname) throws IOException
{
	TakesScreenshot ts=(TakesScreenshot)driver;
	File source=ts.getScreenshotAs(OutputType.FILE);
	File target=new File(System.getProperty("user.dir")+"/ScreenShots/"+tname+".png");
	FileUtils.copyFile(source, target);
	System.out.println("Screen shot taken");
	
}
public String randomString()
{
	String generatedString=RandomStringUtils.randomAlphabetic(8);
	return (generatedString);
}
public String randomNum()
{
	String generatedString1=RandomStringUtils.randomNumeric(4);
	return (generatedString1);
	
}
}
