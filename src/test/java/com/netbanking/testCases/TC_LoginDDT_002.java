package com.netbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.netbanking.pageObjects.LoginPage;
import com.netbanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass 
{
@Test(dataProvider="LoginData")
public void loginDDT(String user,String pwd) throws InterruptedException
{
	LoginPage lp=new LoginPage(driver);
	lp.setUserName(user);
	lp.setPassword(pwd);
	logger.info("UserName provided");
	lp.clickSubmit();
	
	Thread.sleep(3000);
	
	if(isAlertPresent()==true)
	{
		driver.switchTo().alert().accept();
		driver.switchTo().defaultContent();
		Assert.assertTrue(false);
		logger.warn("login failed");
	}
	else
	{
		Assert.assertTrue(true);
		logger.info("Login passed");
		lp.clickLogout();
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		driver.switchTo().defaultContent();
	}
}
public boolean isAlertPresent()
{
	try
	{
		driver.switchTo().alert();
		return true;
	}
	catch (NoAlertPresentException e) 
	{
		return false;
	}
}
@DataProvider(name="LoginData")
String [][] getData() throws IOException
{
	String path="C:\\Users\\Mukhesh Kummithi\\eclipse-workspace\\NETBANKINGV1\\src\\test\\java\\com\\netbanking\\testData\\LoginData.xlsx";
	int rownum=XLUtils.getRowCount(path, "Sheet1");
	int colcount=XLUtils.getCellCount(path, "Sheet1", 1);
	String loginData[][]=new String[rownum][colcount];
	for(int i=1;i<=rownum;i++)
	{
		for(int j=0;j<colcount;j++)
		{
			loginData[i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j);
		}
	}
	return loginData;
}
}
