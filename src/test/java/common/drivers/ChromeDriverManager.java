package common.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverManager {
	private static WebDriver driver = null;

	public WebDriver getDriver() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/webdrivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
//		options.addArguments("--start-fullscreen");
//		options.addArguments("headless");
		return getInstance();
	}

	public static synchronized WebDriver getInstance() {
		if(driver == null) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--user-data-dir=C:\\Users\\richa\\AppData\\Local\\Google\\Chrome\\User Data\\Selenium");
			options.addArguments("--disable-extensions");
			options.addArguments("--start-maximized");
//        	options.addArguments("--start-fullscreen");
//			options.addArguments("headless");
			driver = new ChromeDriver(options);
		}
		return driver;
	}
}