/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package test.af;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestAddTransaction {

	@Test
	public void addTransactionTest() {

		System.setProperty("webdriver.chrome.driver", "Driver/chromedriver79");
		WebDriver driver = new ChromeDriver();
		String price = "19.95";
		
		// open login page
		driver.get("http://localhost:8080/weSpendProject/LoginForm.jsp");

		// login with "username" and "password"
		driver.findElement(By.xpath("//*[@id=\"user\"]")).sendKeys("username");
		driver.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys("password");

		// try login
		driver.findElement(By.xpath("/html/body/div/div/form/input[1]")).click();

		// open add transaction page
		driver.findElement(By.xpath("//*[@id=\"navbarDropdownMenuLink\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"navbarNavDropdown\"]/ul/li[1]/div/a")).click();

		// setup price
		driver.findElement(By.xpath("//*[@id=\"value\"]")).sendKeys(price);
		driver.findElement(By.xpath("//*[@id=\"comment\"]")).sendKeys("Cinema with friends");

		// try to create debt
		driver.findElement(By.xpath("//*[@id=\"debt\"]")).click();

		// retrieve the price
		WebElement valueParagraph = driver.findElement(By.xpath("/html/body/div/div[1]/div[1]/div/div/p[1]"));

		assertEquals (null, price, valueParagraph.getAttribute("value"));
	
		driver.close();
	}
}
