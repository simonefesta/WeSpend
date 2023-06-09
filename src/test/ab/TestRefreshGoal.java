package test.ab;

// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;
public class TestRefreshGoal {
  private static final String INPUTNAME = "inputName";
  private static final String INPUTNUMBER = "inputNumber";
  private static final String INPUTLINK = "inputLink";
  private static final String INPUTDESCRIPTION = "inputDescription";
  private static final String USER = "user";
  private static final String TEST = "test";
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
	System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void testDeleteGoal() {
	  driver.get("http://localhost:55122/weSpendProject/LoginForm.jsp");
	    driver.findElement(By.id(USER)).click();
	    driver.findElement(By.id(USER)).sendKeys("brugnana");
	    driver.findElement(By.id("pass")).sendKeys("ciaociao");
	    driver.findElement(By.name("submit")).click();
	    driver.findElement(By.linkText("Goal")).click();
	    driver.findElement(By.linkText("View goal")).click();
	    driver.findElement(By.cssSelector(".col-md-2 > .btn")).click();
	    driver.findElement(By.id(INPUTNAME)).click();
	    driver.findElement(By.id(INPUTNAME)).sendKeys("test");
	    driver.findElement(By.id(INPUTNUMBER)).click();
	    driver.findElement(By.id(INPUTNUMBER)).sendKeys("123");
	    driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/form/div[1]")).click();
	    driver.findElement(By.id(INPUTLINK)).click();
	    driver.findElement(By.id(INPUTLINK)).sendKeys("https://www.ebay.it/itm/Notebook-Lenovo-IdeaPad-S145-15IWL-15-6-Intel-i7-RAM-8GB-SSD-256GB-81MV00S3IX/163856415566?_trkparms=aid%3D111001%26algo%3DREC.SEED%26ao%3D1%26asc%3D20160908105057%26meid%3D2d05bed3302e4c7d8f86811e5d82612d%26pid%3D100675%26rk%3D3%26rkt%3D15%26mehot%3Dpp%26sd%3D352880425206%26itm%3D163856415566%26pmt%3D0%26noa%3D1%26pg%3D2380057&_trksid=p2380057.c100675.m4236&_trkparms=pageci%3A747637e5-47a2-11ea-a226-74dbd18094ff%7Cparentrq%3A127112c41700a4b77c923364ffeb1a3b%7Ciid%3A1");
	    driver.findElement(By.id(INPUTDESCRIPTION)).click();
	    driver.findElement(By.id(INPUTDESCRIPTION)).sendKeys("This is a test goal.");
	    driver.findElement(By.name("submit")).click();
	    driver.findElement(By.linkText(TEST)).click();
	    driver.findElement(By.cssSelector(".btn-info")).click();
	    driver.findElement(By.linkText(TEST)).click();
	    vars.put("var2", driver.findElement(By.xpath("//p[contains(.,\'EUR 549.9\')]")).getText());
	    driver.findElement(By.id(INPUTNUMBER)).sendKeys("123");
	    assertEquals(vars.get("var2").toString(), "EUR 549.9");
  }
}
