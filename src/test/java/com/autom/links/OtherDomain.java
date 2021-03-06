// Display Other Domain Url's compare to BaseUrl
package com.autom.links;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.autom.base.Base;
import com.autom.init.ObjectRespo;
import com.autom.utilities.ExcelUtil;

public class OtherDomain extends Base{
	
	@Test(dataProvider = "linksData")
	public static void getOtherDomainLinks(String BaseUrl, String pageUrl) {
		String url = "";
		driver.navigate().to(pageUrl);

		//collect all the links in the Web Page
		List <WebElement> links = driver.findElements(By.tagName("a"));
		Iterator <WebElement> it = links.iterator();

		while(it.hasNext()){
			//Get the href Value of anchor tag
			url = it.next().getAttribute("href");

			//Check if URL is empty or not
			if(url == null || url.isEmpty()){
				continue;
			}

			//Check URL belong to same domain or not
			if(!url.startsWith(BaseUrl)){
				System.out.println(url);
				continue;
			}
		}
	}

	@DataProvider
	public Object[][] linksData() throws IOException{
		ExcelUtil.getExcel(ObjectRespo.testLinks);
		ExcelUtil.getSheet(1);
		return ExcelUtil.getData();
	}
}