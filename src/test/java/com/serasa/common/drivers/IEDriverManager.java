package com.serasa.common.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class IEDriverManager {
	
	private static WebDriver driver = null;
	
    public WebDriver getDriver() {
       	System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/src/main/resources/com/serasa/webdrivers/IEDriverServer.exe");        
        return getInstance();
    }
    
    @SuppressWarnings("deprecation")
	public static synchronized WebDriver getInstance() {
    	if(driver == null) {
    		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
    		capabilities.setCapability("ignoreZoomSetting", true);
    		driver = new InternetExplorerDriver(capabilities);
    		driver.manage().window().maximize();
    	}
    	return driver;
    }
	
}
