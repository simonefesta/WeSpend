package test.sf;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;

import org.openqa.selenium.JavascriptExecutor;

import java.util.*;


//Generated by Selenium IDE

public class TestSignIn {
	
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUpConfig() {
	System.setProperty("webdriver.chrome.driver", "Driver/chromedriver79");
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<>();
  }
  @After
  public void tearDownConfig() {
    driver.quit();
  }
  @Test
  public void test() {
    driver.get("http://localhost:8080/weSpendProject/LoginForm.jsp");
    driver.manage().window().setSize(new Dimension(1440, 823));
    driver.findElement(By.id("user")).click();
    driver.findElement(By.id("user")).sendKeys("username");
    driver.findElement(By.id("pass")).click();
    driver.findElement(By.id("pass")).sendKeys("password");
    driver.findElement(By.name("submit")).click();
    driver.findElement(By.linkText("Account")).click();
    vars.put("nomeutente", driver.findElement(By.xpath("/html/body/nav/div/ul/li[4]/div/a[1]")).getText());
    assertEquals(vars.get("nomeutente").toString(), "username");
  }
}





