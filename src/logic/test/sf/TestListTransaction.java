/*
 * Copyright 2020 Simone Festa
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package test.sf;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestListTransaction {
	

	@Test
	public void viewTransaction() {
		
		System.setProperty("webdriver.chrome.driver", "Driver/chromedriver79");
		WebDriver driver = new ChromeDriver();
		String date ="2020-02-04 18:24:00.0";
		
		// Go to Login Page
		driver.get("http://localhost:8080/weSpendProject/LoginForm.jsp");

		// Sign in with "username" & "password"
		driver.findElement(By.xpath("//*[@id=\"user\"]")).sendKeys("username");
		driver.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys("password");

		//Click on "login" button
		driver.findElement(By.xpath("/html/body/div/div/form/input[1]")).click();	

		//Check the last transaction is added
		driver.findElement(By.xpath("/html/body/nav/div/ul/li[2]/a")).click();
		driver.findElement(By.xpath("/html/body/nav/div/ul/li[2]/div/a[1]")).click();
		
		WebElement dataorder = driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/th"));
	
		assertEquals (null, date, dataorder.getText());
		
		driver.close();
		
	}

}
