/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package test.af;

// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
public class TestLogOut {
  private static final String USER = "user";
  private static final String PASS = "pass";

  private WebDriver driver;
  JavascriptExecutor js;
  @Before
  public void setUp() {
	System.setProperty("webdriver.chrome.driver", "Driver/chromedriver79");
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void test() {
    driver.get("http://localhost:8080/weSpendProject/LoginForm.jsp");
    driver.manage().window().setSize(new Dimension(1331, 808));
    driver.findElement(By.id(USER)).click();
    driver.findElement(By.id(USER)).sendKeys("username");
    driver.findElement(By.id(PASS)).click();
    driver.findElement(By.id(PASS)).sendKeys("password");
    driver.findElement(By.name("submit")).click();
    driver.findElement(By.linkText("Account")).click();
    driver.findElement(By.linkText("Exit")).click();
    driver.findElement(By.id("navbarDropdownMenuLink")).click();
    driver.findElement(By.linkText("Transaction")).click();
    assertThat(driver.getTitle(), is("weSpend-Login"));
  }
}