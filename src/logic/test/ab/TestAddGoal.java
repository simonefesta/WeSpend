package test.ab;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class TestAddGoal {

	@Test
	void test() 
	{
		System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		String name = "test";
		
		// open login page
		driver.get("http://localhost:55122/weSpendProject/LoginForm.jsp");

		// login with "username" and "password"
		driver.findElement(By.xpath("//*[@id=\"user\"]")).sendKeys("brugnana");
		driver.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys("ciaociao");

		// login
		driver.findElement(By.xpath("/html/body/div/div/form/input[1]")).click();

		// open goal page
		driver.findElement(By.xpath("//*[@id=\"navbarNavDropdown\"]/ul/li[3]")).click();
		driver.findElement(By.xpath("//*[@id=\"navbarNavDropdown\"]/ul/li[3]/div/a")).click();

		// open modal form
		driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div[1]/button")).click();

		// insert data into form
		driver.findElement(By.xpath("//*[@id=\"inputName\"]")).sendKeys("test");
		driver.findElement(By.xpath("//*[@id=\"inputNumber\"]")).sendKeys("200");
		driver.findElement(By.xpath("//*[@id=\"inputDescription\"]")).sendKeys("This is an automatic test.");

		// try to add goal
		driver.findElement(By.xpath("//*[@id=\"Modal1\"]/div/div/div[2]/form/div[5]/button[2]")).click();

		// retrieve the price
		
		WebElement valueParagraph = driver.findElement(By.xpath("//*[@id=\"0\"]"));

		//check text
		assertEquals (null, name, valueParagraph.getText());
	}

}
