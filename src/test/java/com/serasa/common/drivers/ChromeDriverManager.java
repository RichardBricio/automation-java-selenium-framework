package com.serasa.common.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverManager {
	private static WebDriver driver = null;

	public WebDriver getDriver() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/com/serasa/webdrivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--start-fullscreen");
		return getInstance();
	}

	public static synchronized WebDriver getInstance() {
		if(driver == null) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
//        options.addArguments("--start-fullscreen");
			driver = new ChromeDriver(options);
		}
		return driver;
	}
}