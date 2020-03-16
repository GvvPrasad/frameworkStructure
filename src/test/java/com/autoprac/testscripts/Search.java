package com.autoprac.testscripts;

import com.autoprac.common.Base;
import com.autoprac.locators.HomePage;
import com.autoprac.utilities.ExcelUtil;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;

@Listeners(com.autoprac.listeners.TestNGListener.class)
public class Search extends Base {
	public static String filePath = projectPath+"//testDataFiles//TestLinks.xlsx";

	@BeforeSuite
	public static void beforeSuite() throws IOException {
		Base.browserSetUp();
		ExcelUtil.getExcel(filePath);
	}


	@Test
	public static void searchProduct() throws IOException {
		HomePage hp = PageFactory.initElements(driver, HomePage.class);

		ExcelUtil.getSheet(1);
		String value = ExcelUtil.getStringValue(1, 0);
		hp.searchbox().sendKeys(value);
		hp.searchbox().sendKeys(Keys.RETURN);
		log.info("searched item");	
	}

	
	@AfterSuite
	public static void afterSuite() throws Exception {
		Base.driverclose();
	}
}