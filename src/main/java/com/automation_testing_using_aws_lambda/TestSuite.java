package com.automation_testing_using_aws_lambda;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSuite {
	public static WebDriver driver;
	
	@Test
	public void login() { 
		System.out.println("hiihi");
		
		File file = new File("/tmp/chromedriver");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		ChromeOptions options = new ChromeOptions();
		options.setBinary("/tmp/headless-chromium");
		options.addArguments("headless");
		options.addArguments("window-size=1366,768");
		options.addArguments("--single-process");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--no-sandbox");
		options.addArguments("--user-data-dir=/tmp/user-data");
		options.addArguments("--data-path=/tmp/data-path");
		options.addArguments("--homedir=/tmp");
		options.addArguments("--disk-cache-dir=/tmp/cache-dir");

		driver = new ChromeDriver(options);
		

		driver.get("https://www.linkedin.com/login");

		WebElement username = driver.findElement(By.id("username"));

		WebElement password = driver.findElement(By.id("password"));

		WebElement login = driver.findElement(By.xpath("//button[text()='Sign in']"));

		username.sendKeys("Username Here");
		password.sendKeys("Password here");

		login.click();
		String actualUrl = "https://www.linkedin.com/feed/";
		String expectedUrl = driver.getCurrentUrl();
		Assert.assertEquals(expectedUrl, actualUrl);
	}
	
	@Test
	public void treesdown() {
		driver.close();
		driver.quit();
	}
}
